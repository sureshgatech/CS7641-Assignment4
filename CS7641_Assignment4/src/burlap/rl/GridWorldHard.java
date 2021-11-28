package burlap.rl;

import burlap.rl.util.AnalysisAggregator;
import burlap.rl.util.AnalysisRunner;
import burlap.rl.util.BasicRewardFunction;
import burlap.rl.util.BasicTerminalFunction;
import burlap.rl.util.MapPrinter;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.singleagent.explorer.VisualExplorer;
import burlap.oomdp.visualizer.Visualizer;

public class GridWorldHard {
	//These are some boolean variables that affect what will actually get executed
	private static boolean visualizeInitialGridWorld = true; //Loads a GUI with the agent, walls, and goal
	
	//runValueIteration, runPolicyIteration, and runQLearning indicate which algorithms will run in the experiment
	private static boolean runValueIteration = false;
	private static boolean runPolicyIteration = false;
	private static boolean runQLearning = true;
	
	//showValueIterationPolicyMap, showPolicyIterationPolicyMap, and showQLearningPolicyMap will open a GUI
	//you can use to visualize the policy maps. Consider only having one variable set to true at a time
	//since the pop-up window does not indicate what algorithm was used to generate the map.
	private static boolean showValueIterationPolicyMap = true;
	private static boolean showPolicyIterationPolicyMap = true;
	private static boolean showQLearningPolicyMap = true;
	
	private static Integer MAX_ITERATIONS = 1000;
	private static Integer NUM_INTERVALS = 1000;

	protected static int[][] userMap = new int[][] {
					{ 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0},
					{ 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
					{ 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
					{ 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0},
					{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
					{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
					{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
					{ 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
					{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
					{ 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0},
					{ 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0},
					{ 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
					{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
					{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
					{ 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1},
					{ 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
					{ 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
					{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
					{ 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},};

//	private static Integer mapLen = map.length-1;

	public static void main(String[] args) {
		// convert to BURLAP indexing
		int[][] map = MapPrinter.mapToMatrix(userMap);
		int maxX = map.length-1;
		int maxY = map[0].length-1;
		// 

		BasicGridWorld gen = new BasicGridWorld(map,maxX,maxY); //0 index map is 11X11
		Domain domain = gen.generateDomain();

		State initialState = BasicGridWorld.getExampleState(domain);


		BasicRewardFunction rf = new BasicRewardFunction(maxX,maxY); //Goal is at the top right grid
		/*for(int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				if (i == maxX  && j == maxY) {
					rf.setReward(i, j, 1000);
				} else if (map[i][j] == 0) {
					rf.setReward(i, j, -5);
				}
			}
			//rf.setReward(2,6,-100);
			//rf.setReward(2,7,-100);
			//rf.setReward(2,8,-100);
			//rf.setReward(2,9,-10);
			//rf.setReward(2,10,-10);
			//rf.setReward(4,10,-100);
			//rf.setReward(4,10,-100);
			//rf.setReward(4,10,-100);
			//rf.setReward(5,10,-10);
			//rf.setReward(6,10,-10);
			//rf.setReward(8,5,-100);
			//rf.setReward(9,5,-10);
			//rf.setReward(10,5,-10);
			//rf.setReward(11,14,-10);
			//rf.setReward(11,15,-10);
			//rf.setReward(16,16,-10);
			//rf.setReward(16,12,-100);
			//rf.setReward(16,13,-100);
			//rf.setReward(16,14,-100);
			//rf.setReward(16,15,-100);
		}*/
		TerminalFunction tf = new BasicTerminalFunction(maxX,maxY); //Goal is at the top right grid
		
		SimulatedEnvironment env = new SimulatedEnvironment(domain, rf, tf,
				initialState);
		//Print the map that is being analyzed
		System.out.println("/////Hard Grid World 2x2 Analysis/////\n");
		MapPrinter.printMap(MapPrinter.matrixToMap(map));
		
		if (visualizeInitialGridWorld) {
			visualizeInitialGridWorld(domain, gen, env);
		}

		AnalysisRunner runner = new AnalysisRunner(MAX_ITERATIONS,NUM_INTERVALS);
		double[] gammaArray={.1,.3,.5,.7,.9};
		for(int i=0; i<gammaArray.length;i++) {
			double gamma=gammaArray[i];
			if (runValueIteration) {
				runner.runValueIteration(gen, domain, initialState, rf, tf, showValueIterationPolicyMap, gamma);
			}
			if (runPolicyIteration) {
				runner.runPolicyIteration(gen, domain, initialState, rf, tf, showPolicyIterationPolicyMap, gamma);
			}
			if (runQLearning) {
				runner.runQLearning(gen, domain, initialState, rf, tf, env, showQLearningPolicyMap, gamma);
			}
			AnalysisAggregator.printAggregateAnalysis();
		}
	}


	private static void visualizeInitialGridWorld(Domain domain,
			BasicGridWorld gen, SimulatedEnvironment env) {
		Visualizer v = gen.getVisualizer();
		VisualExplorer exp = new VisualExplorer(domain, env, v);

		exp.addKeyAction("w", BasicGridWorld.ACTIONNORTH);
		exp.addKeyAction("s", BasicGridWorld.ACTIONSOUTH);
		exp.addKeyAction("d", BasicGridWorld.ACTIONEAST);
		exp.addKeyAction("a", BasicGridWorld.ACTIONWEST);

		exp.initGUI();

	}
	

}
