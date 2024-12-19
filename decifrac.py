import math
def factor(n):
	factors=set([])	
	for i in range(2,n+1):
		if(n%i==0):
			factors.add(i)
	return factors
numstr=input("Enter Decimal:")
num=float(numstr)
acc=len(numstr[numstr.index("."):])-1
dlen=int(input("Enter max denominator size:"))
m=int(input("Enter 1 for floored value and 2 for rounded value:"))
flag=False
for i in range(1,(10**dlen)+1):
	for j in range(1,math.ceil(num)*(i+1)):
		p=False
		if(j==999 and i==998):
			print(math.floor((j/i)*(10**acc)))
			print(num*(10**acc))
			print(math.floor(num*(10**acc)))
		if(m==1):
			if(math.floor((j/i)*(10**acc))==math.floor(num*(10**acc))):	
				p=True
		else:
			if(round((j/i)*(10**acc))==round(num*(10**acc))):
				p=True
		
		if(p):
			if(factor(j).isdisjoint(factor(i))):
				print(j,"/",i,end="::")
				print(j/i)
		flag=flag or p
if(not flag):
	print("no solutions try a higher denominator size or mode")
input("Press enter to close")