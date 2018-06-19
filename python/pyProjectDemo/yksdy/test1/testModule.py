'''
@author: mayn
'''
import this

class MyClass(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
    def isLeapYear(self, year):
        if(year%100!=0 and year%4==0 or year%400==0):
            return True
        else:
            return False
    def isWhileDay(self, day):
        pass
        
if __name__ == "__main__":
    print(MyClass().isLeapYear(300))
    print(MyClass().isLeapYear(3453))
    print(MyClass().isLeapYear(1200))
    print(MyClass().isLeapYear(1321))