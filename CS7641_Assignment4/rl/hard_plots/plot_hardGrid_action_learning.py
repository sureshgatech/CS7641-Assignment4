import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pdb

action_file = 'hard_learning_actions.csv'

with open(action_file,'r') as fr:
	content_tmp = fr.readline()

	content_tmp = fr.readline()
	Q_action1 = content_tmp[11:-1].split(",")
	content_tmp = fr.readline()
	Q_action2 = content_tmp[11:-1].split(",")
	content_tmp = fr.readline()
	Q_action3 = content_tmp[11:-1].split(",")
	content_tmp = fr.readline()
	Q_action4 = content_tmp[11:-1].split(",")
	content_tmp = fr.readline()
	Q_action5 = content_tmp[11:-1].split(",")


Q_action1 = [int(numeric_string) for numeric_string in Q_action1]
Q_action2 = [int(numeric_string) for numeric_string in Q_action2]
Q_action3 = [int(numeric_string) for numeric_string in Q_action3]
Q_action4 = [int(numeric_string) for numeric_string in Q_action4]

Q_action5 = [float(numeric_string) for numeric_string in Q_action5]
plt.plot(range(1,201), Q_action1, color ='g', linewidth ='1', label='learningrate 0.3')
plt.plot(range(1,201), Q_action2, color ='b', linewidth ='1', label='learningrate 0.5')
#plt.plot(range(1,201), Q_action3, color ='r', linewidth ='1', label='learningrate 0.7')
#plt.plot(range(1,201), Q_action4, color ='y', linewidth ='1', label='learningrate 0.9')
#plt.plot(range(1,201), Q_action4, color ='m', linewidth ='1', label='learningrate 0.98')
plt.xlabel('Q Learning Iterations',  fontsize = 13)
plt.ylabel('Actions',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(0,5000)
#plt.xlim(0,500)
plt.grid(True)
plt.legend(loc = 'best')
plt.show()
#plt.savefig('hard_grid_valueI_action.png')
plt.close()