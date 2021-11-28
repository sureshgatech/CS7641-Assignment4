package burlap.rl.util;

import burlap.rl.BasicGridWorld;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.RewardFunction;

public class BasicRewardFunction implements RewardFunction {
	protected double [][] rewardMatrix;
	int goalX;
	int goalY;

	public BasicRewardFunction(int goalX, int goalY) {
		this.goalX = goalX;
		this.goalY = goalY;
		this.rewardMatrix = new double[goalX+1][goalX+1];
	}

	//@Override
	//public void setReward(int x, int y, double r){
	//	this.rewardMatrix[x][y] = r;
	//}

	@Override
	public double reward(State s, GroundedAction a, State sprime) {

		// get location of agent in next state
		ObjectInstance agent = sprime.getFirstObjectOfClass(BasicGridWorld.CLASSAGENT);
		int ax = agent.getIntValForAttribute(BasicGridWorld.ATTX);
		int ay = agent.getIntValForAttribute(BasicGridWorld.ATTY);

		// are they at goal location?
		if (ax == this.goalX && ay == this.goalY) {
			return 100.;
		}

		return -1;


		//System.out.println("reward"+	 rewardMatrix[ax][ay]);

	//return rewardMatrix[ax][ay];
	}


	public void setReward(int i, int j, int r) {

		this.rewardMatrix[i][j] = r;
	}

}
