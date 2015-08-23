/* 
 * @author GA_237
 */
import static org.junit.Assert.*;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import org.junit.Before;
import org.junit.Test;

//GA_237
public class BeMetest {
    private static ActorWorld world;
    private static BeMeCritter testBeMeCritter;
    
    @Before
    public void init() {
                //init the test
        world = new ActorWorld(new BoundedGrid<Actor>(20, 20));
        testBeMeCritter = new BeMeCritter();
    }
    
        // test weather can turn the color true
    @Test
    public void test1() { 
        assertEquals(testBeMeCritter.getColor(), Color.gray);
        testBeMeCritter.setColor(Color.green);
        assertEquals(testBeMeCritter.getColor(), Color.green);
    }

        // test weather can make a Critter to a BeMeCritter
    @Test
    public void test2() {
        world.add(new Location(10, 10), testBeMeCritter);
        world.add(new Location(9, 8), new Rock());
        world.add(new Location(9, 9), new Rock());
        world.add(new Location(9, 10), new Rock());
        world.add(new Location(9, 11), new Rock());
        world.add(new Location(10, 8), new Rock());
        world.add(new Location(10, 11), new Rock());
        world.add(new Location(11, 8), new Rock());
        world.add(new Location(11, 9), new Rock());
        world.add(new Location(11, 10), new Rock());
        world.add(new Location(11, 11), new Rock());
        world.add(new Location(10, 9), new Critter());
        
        //If don't act
        Actor actor = world.getGrid().get(new Location(10, 9));  
                //don't turn
        assertFalse(actor instanceof BeMeCritter);               
        
        //If act
                testBeMeCritter.act();
                //turn the Critter to a BeMeCritter                                  
        assertFalse(actor instanceof BeMeCritter);             
    }
}
