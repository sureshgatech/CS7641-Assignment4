import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pdb

time_file = 'hard_grid_time.csv'

with open(time_file,'r') as fr:
	content_tmp = fr.readline()
	
	content_tmp = fr.readline()
	iteration = content_tmp[11:-1].split(",")
	
	content_tmp = fr.readline()
	valueI_time = content_tmp[16:-1].split(",")

	content_tmp = fr.readline()
	policyI_time = content_tmp[17:-1].split(",")
	
	content_tmp = fr.readline()
	Q_time = content_tmp[11:-1].split(",")
	
	print('iteration = ' + str(iteration))
	print('valueI_time = ' + str(valueI_time))
	print('policyI_time = ' + str(policyI_time))
	print('Q_time = ' + str(Q_time))
	
	
valueI_time = [int(numeric_string) for numeric_string in valueI_time]
plt.plot(range(1,1001), valueI_time, color ='g', linewidth ='1')
plt.xlabel('VI Iterations',  fontsize = 13)
plt.ylabel('VI Time',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(0,3000)
#plt.xlim(0,500)
plt.grid(True)
#plt.show()
plt.savefig('hard_grid_valueI_time.png')
plt.close()

policyI_time = [int(numeric_string) for numeric_string in policyI_time ]
plt.plot(range(1,1001), policyI_time, c = 'g',linewidth ='1')
plt.xlabel('PI Iterations',  fontsize = 13)
plt.ylabel('PI Time',  fontsize = 13)
# plt.legend(('EM', 'Kmeans'), loc = 4, fontsize = 13)
plt.ylim(0,5000)
plt.tight_layout()
plt.grid(True)
#plt.show()
plt.savefig('hard_grid_policyI_time.png')
plt.close()
