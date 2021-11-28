import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pdb

action_file = 'hard_grid_number_of_action.csv'

with open(action_file,'r') as fr:
	content_tmp = fr.readline()

	content_tmp = fr.readline()
	iteration = content_tmp[11:-1].split(",")

	content_tmp = fr.readline()
	valueI_action = content_tmp[16:-1].split(",")

	content_tmp = fr.readline()
	policyI_action = content_tmp[17:-1].split(",")

	content_tmp = fr.readline()
	Q_action = content_tmp[11:-1].split(",")

#print('iteration = ' + str(iteration))
#	print('valueI_action = ' + str(valueI_action))
#	print('policyI_action = ' + str(policyI_action))
#	print('Q_action = ' + str(Q_action))

print(valueI_action)
valueI_action = [int(numeric_string) for numeric_string in valueI_action]
plt.plot(range(1,1001), valueI_action, color ='g', linewidth ='1')
plt.xlabel('VI Iterations',  fontsize = 13)
plt.ylabel('VI Actions',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(0,100)
#plt.xlim(0,500)
plt.grid(True)
#plt.show()
plt.savefig('hard_grid_valueI_action.png')
plt.close()

policyI_action = [int(numeric_string) for numeric_string in policyI_action ]
plt.plot(range(1,1001), policyI_action, c = 'g',linewidth ='1')
plt.xlabel('PI Iterations',  fontsize = 13)
plt.ylabel('PI Actions',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(0,100)
plt.tight_layout()
plt.grid(True)
#plt.show()
plt.savefig('hard_grid_policyI_action.png')
plt.close()
