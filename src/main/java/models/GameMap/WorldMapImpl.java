package models.GameMap;

import java.util.Collections;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;

import controllers.MOVEMENT;
import javafx.util.Pair;
import models.Character.EnemyImp;
import models.Character.Player;
import models.Character.PlayerImp;
import models.deck.Deck;
import models.deck.DeckImpl;


public final class WorldMapImpl implements WorldMap {

    /**
     * logic number of row for the map.
     */
    public static final int NUM_ROW = 9;

    /**
     * logic numer of columns for the map.
     */
    public static final int NUM_COL = 9;


    private List<EnemyImp> enemies;
    private EnemyImp boss;
    private Player player;

    public WorldMapImpl() {
        this.player = new PlayerImp(new DeckImpl());

        this.enemies = this.generateCrossPositions(Point2DImp.setPoint(0, NUM_COL / 2))
                .map(p -> new EnemyImp(new DeckImpl(), p))
                .collect(Collectors.toList());


        this.boss = new EnemyImp(new DeckImpl(), Point2DImp.setPoint(NUM_ROW / 2, NUM_COL / 2));

    }

    /**
     * generate a Point2D stream of cross positions from the center of the map to be assigned to enemies.
     * it performs first a 90 degree rotation for starting point and after that an additional 180 degree
     * to the 2 resulting points, this is applied by flatting and mapping to the 90 degree starting point
     * and to the resulting two point by flatting each of these points and mapping them to their 180 degree coordinate 
     * @param start point to start the generation from.
     * @return a stream of cross positions
     */
    private Stream<Point2D> generateCrossPositions(final Point2D start) {
        return Stream.of(start)
                .flatMap(p -> Stream.of(p, Point2DImp.setPoint(start.getY(), NUM_ROW - p.getX() - 1)))
                .flatMap(p -> Stream.of(p, Point2DImp.setPoint(NUM_COL - p.getX() - 1, NUM_COL - p.getY() - 1)));
    }

    /**
     * check wether the adjacent positions from the argument have some enemies.
     * @param point point to check the adjacence
     * @return the first adjacent enemy founded, otherwise an empty Optional
     */
    private Optional<EnemyImp> getEnemyIfAdjacent(final Point2D point) {
        return IntStream.rangeClosed(point.getX() - 1, point.getX() + 1)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(point.getY() - 1, point.getY() + 1)
                        .mapToObj(y -> Point2DImp.setPoint(x, y)))
                .flatMap(p -> this.getEnemies()
                        .stream()
                        .filter(e -> e.getPosition().equals(p)))
                .findAny();

    }

    /**
     * check wether the destination is occupied by an enemy.
     * @param point destination point
     * @return true if no collision is detected, otherwise false
     */
    private boolean noCollisionDetected(final Point2D point) {
        return Stream.of(point)
                .flatMap(p -> this.getEnemies().stream())
                .noneMatch(e -> e.getPosition().equals(point));
    }

    /**
     * check wether the destination point is outside of the map dimension.
     * @param point destination point
     * @return true if destination is in bound, otherwise false
     */
    private boolean isPositionInBounds(final Point2D point) {
        return IntStream.range(0, NUM_ROW).boxed()
                .flatMap(x -> IntStream.range(0, NUM_COL)
                        .mapToObj(y -> Point2DImp.setPoint(x, y)))
                .anyMatch(p -> p.equals(point));
    }



    @Override
    public Player getPlayer() {
        return this.player;
    }


    @Override
    public Optional<EnemyImp> playerInteract() {
        return this.getEnemyIfAdjacent(this.player.getPosition());
    }

    @Override
    public Optional<EnemyImp> getBoss() {
        return this.getEnemies().contains(this.boss) ? Optional.of(this.boss) : Optional.empty();
    }

    @Override
    public List<EnemyImp> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

    @Override
    public Optional<Point2D> updatePlayerPosition(final MOVEMENT direction) {

        return Stream.of(this.player)
                .flatMap(p -> Stream.of(player.getPosition())
                        .map(point -> direction.getDestFor(point))
                        .filter(this::isPositionInBounds)
                        .filter(this::noCollisionDetected)
                        .map(point -> {
                            p.setPosition(point);
                            return point;
                        }))
                .findAny();

    }


    @Override
    public void removeEnemy(final EnemyImp enemy) {

        if (this.getBoss().isPresent()) {
            this.enemies.remove(0);
            return;
        }

        this.enemies.remove(enemy);

        if (this.enemies.isEmpty()) {
            this.enemies.add(boss);
        }
    }

    @Override
    public void setPlayer(final Player player) {
        player.setPosition(this.player.getPosition());
        this.player = player;
    }




}
