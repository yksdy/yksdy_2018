'''
Created on 2018年4月26日

@author: mayn
'''
from django.http import HttpResponse

def hello(request):
    return HttpResponse("Hello world! first django")