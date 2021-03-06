package com.github.cop4331sum13.entities;

/**
 * This class defines the Tank that the user will control.
 * 
 * @author Earth's Defenders
 */
public abstract class Tank extends Entity
{
	/**
	 * Defines the top speed that the user's tank can move at.  This variable can be modified for balance purposes.
	 */
	private static double tankSpeedLimit;
	
	/**
	 * Constructs a Tank object when a level starts.
	 * 
	 * @param xPos - x coordinate to spawn Tank with.
	 * @param yPos - y coordinate to spawn Tank with.
	 * @param xVel - x velocity to spawn Tank with.
	 * @param yVel - y velocity to spawn Tank with.
	 */
	public Tank( double xPos, double yPos, double xVel, double yVel )
	{
		//  Call parent class constructor.
		super( xPos, yPos, xVel, yVel );
		
		
	}
	
	
	
	/**
	 * This version of the move() method of ancestor class entity ensures that tank does
	 * not wrap around the screen when the edge is reached.
	 */
	@Override
	public void move()
	{
		//  If tank is too close to left edge of screen, stop it.
		if( xPosition + xVelocity <= 35 )
		{
			xPosition = 35;
			xVelocity = 0;
		}
		//  If tank is too close to right edge of screen, stop it.
		else if( xPosition + xVelocity >= WIDTH - 55 )
		{
			xPosition = WIDTH - 55;
			xVelocity = 0;
		}
		//  Move as normal.
		else
		{
			xPosition = xPosition + xVelocity;
		}
		
		
	}
	
	
	
	/**
	 * This method increases the x velocity to the right of the window.
	 */
	public void accelerateRight()
	{
		//  Increase the tank's velocity to the right
		this.xVelocity += 0.7;
		
		
		//  Ensure tank does not go faster than permitted speed limit.
		if( this.xVelocity > tankSpeedLimit )
		{
			this.xVelocity = tankSpeedLimit;
		}
		
		
	}
	
	
	
	/**
	 * This method increases the x velocity to the left of the window.
	 */
	public void accelerateLeft()
	{
		//  Increase the tank's velocity to the right
		this.xVelocity -= 0.7;
		
		
		//  Ensure tank does not go faster than permitted speed limit.
		if( this.xVelocity < 0.0 - tankSpeedLimit )
		{
			this.xVelocity = 0.0 - tankSpeedLimit;
		}
		
		
	}
	
	
	
	/**
	 * This method modifies the tank's speed limit.  MUST BE CALLED at start of game, and can be later modified by
	 * Upgrade class.
	 * 
	 * @param tankSL - new tank speed limit to implement.
	 */
	public void setTankSpeedLimit( int tankSL )
	{
		//  Adjust tankSpeedLimit.
		tankSpeedLimit = tankSL;
		
		
	}
	
	
	
	/**
	 * This method lowers the tank's speed when the user let's go of the throttle keys.
	 */
	public void lowerTankSpeed()
	{
		//  If heading towards right (positive x), subtract from current velocity.
		if( xVelocity > 0 )
		{
			//  If speed is equal to or greater than 1.0, subtract 1.0.
			if( xVelocity >= 1.0 )
			{
				xVelocity -= 1.0;
			}
			//  Otherwise, simply set velocity to 0.
			else
			{
				xVelocity = 0;
			}
		}
		
		//  If heading towards left (negative x), add to current velocity.
		else if( xVelocity < 0 )
		{
			//  If speed is equal to or less than -1.0, add 1.0.
			if( xVelocity <= -1.0 )
			{
				xVelocity += 1.0;
			}
			//  Otherwise, simply set velocity to 0.
			else
			{
				xVelocity = 0;
			}
		}
		
		
	}
	
	
	
}
