from random import *
from datetime import *
from texttable import *

def generate_test(array, DIM):
    if len(array) == DIM:
        # print (array)
        pass
    if len(array) > DIM:
        return
    array.append(0)
    for i in range(0, DIM):
        array[-1] = i
        generate_test(array[:], DIM)

def backtracking(array, DIM):
    if len(array) == DIM:
        # print(array)
        pass
    if len(array) > DIM:
        return
    array.append(0)
    for i in range(0, DIM):
        array[-1] = i
        if len(set(array)) == len(array):
            backtracking(array[:], DIM)
            

'''
Util function to convert a timedelta into a number of miliseconds
'''
def millis_interval(start, end):
    """start and end are datetime instances"""
    diff = end - start
    millis = diff.days * 24 * 60 * 60 * 1000
    millis += diff.seconds * 1000
    millis += diff.microseconds / 1000
    return int(millis)

'''
And here we build our experiment
'''
sort_functions = [generate_test, backtracking]    
data_sizes = [3, 4, 5, 6, 7]


t = Texttable()
t.add_row(['Functions'] + data_sizes)
for function in sort_functions:
    row = [function.__name__]
    for size in data_sizes:
        t1 = datetime.now()
        function([], size)
        t2 = datetime.now()        
        row = row + [millis_interval(t1, t2)]
    t.add_row(row)
print(t.draw())        
