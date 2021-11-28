import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pdb

reward_file = '../easy_grid_reward.csv'

with open(reward_file,'r') as fr:
	content_tmp = fr.readline()
	
	content_tmp = fr.readline()
	iteration = content_tmp[11:-1].split(",")
	
	content_tmp = fr.readline()
	valueI_reward = content_tmp[24:-1].split(",")

	content_tmp = fr.readline()
	policyI_reward = content_tmp[25:-1].split(",")
	
	content_tmp = fr.readline()
	Q_reward = content_tmp[19:-1].split(",")
	
	print('iteration = ' + str(iteration))
	print('valueI_reward = ' + str(valueI_reward))
	print('policyI_reward = ' + str(policyI_reward))
	print('Q_reward = ' + str(Q_reward))
	
	
print(valueI_reward)
valueI_reward = [float(numeric_string) for numeric_string in valueI_reward]
plt.plot(range(1,1001), valueI_reward, color ='g', linewidth ='1')
plt.xlabel('Iterations',  fontsize = 13)
plt.ylabel('Reward',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(-100,100)
#plt.xlim(0,500)
plt.grid(True)
#plt.show()
plt.savefig('easy_grid_valueI_reward.png')
plt.close()

policyI_reward = [float(numeric_string) for numeric_string in policyI_reward ]
plt.plot(range(1,1001), policyI_reward, c = 'g',linewidth ='1')
plt.xlabel('Iterations',  fontsize = 13)
plt.ylabel('Reward',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(-100,100)
plt.tight_layout()
plt.grid(True)
#plt.show()
plt.savefig('easy_grid_policyI_reward.png')
plt.close()

Q_reward = [float(numeric_string) for numeric_string in Q_reward ]
plt.plot(range(1,1001), Q_reward, c = 'g',linewidth ='1')
plt.xlabel('Iterations',  fontsize = 13)
plt.ylabel('Reward',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
#plt.ylim(0,1000)
plt.tight_layout()
plt.grid(True)
#plt.show()
plt.savefig('easy_grid_Q_reward.png')
plt.close()