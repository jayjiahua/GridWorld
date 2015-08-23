import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {
	private ActorWorld world = new ActorWorld();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testJumperColor() {
		Jumper alice = new Jumper(Color.ORANGE);
        assertEquals(Color.ORANGE, alice.getColor());
	}

	@Test
	public void testTurn() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		alice.turn(); // trun to random degree
		assertTrue(alice.getDirection() % 45 == 0);
	}

	
	@Test
	public void testJump() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		alice.jump();
		assertEquals(new Location(3, 5), alice.getLocation());
		alice.jump();
		assertEquals(new Location(1, 5), alice.getLocation());
		
		// out of grid
		alice.jump();
		assertEquals(null, alice.getGrid());
	}
	
	/**
	 * Test the condition which both forward two cells can be reached 
	 */
	@Test
	public void testAct1() {
		Jumper alice = new Jumper();
		world.add(new Location(3, 5), alice);
		alice.act();
		assertEquals(new Location(1, 5), alice.getLocation());
		alice.act();
		assertEquals(new Location(0, 5), alice.getLocation());
	}
	
	/**
	 * Test the condition if the location in front of it is empty, 
	 * but the location two cells in front contains a rock
	 */
	@Test
	public void testAct2() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		world.add(new Location(3, 5), new Rock());
		alice.act();
		assertEquals(new Location(4, 5), alice.getLocation());
	}
	
	/**
	 * Test the condition if the location two cells in front of it is empty, 
	 * but the location in front contains a flower or a rock
	 */
	@Test
	public void testAct3() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		world.add(new Location(4, 5), new Rock());
		alice.act();
		assertEquals(new Location(3, 5), alice.getLocation());
	}
	
	/**
	 * Test the condition if both two location contain a rock
	 */
	@Test
	public void testAct4() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		world.add(new Location(4, 5), new Rock());
		world.add(new Location(3, 5), new Rock());
		alice.act();
		assertEquals(new Location(5, 5), alice.getLocation());
	}
	
	
	
	
	@Test
	public void testFlower() {  //Test Jumper weather can take over the flower
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		world.add(new Location(3, 5), new Flower());
		world.add(new Location(4, 5), new Rock());
		alice.act();
		assertEquals(new Location(3, 5), alice.getLocation());
	}
	
	@Test
	public void testCanMJ() {  //Test canMove(), canJump()
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		world.add(new Location(3, 5), new Flower());
		world.add(new Location(4, 5), new Rock());
		assertFalse(alice.canMove());
		assertTrue(alice.canJump());
	}

	
	
	
	@Test
	public void testCanPutOntoLocation() {
		Jumper alice = new Jumper();
		world.add(new Location(5, 5), alice);
		
		world.add(new Location(4, 5), new Flower());
		assertTrue(alice.canPutOntoLocation(new Location(4, 5)));
		
		world.add(new Location(3, 5), new Rock());
		assertFalse(alice.canPutOntoLocation(new Location(3, 5)));
		
		Jumper bob = new Jumper();
		world.add(new Location(6, 6), bob);
		assertFalse(bob.canPutOntoLocation(alice.getLocation()));
	}

}
