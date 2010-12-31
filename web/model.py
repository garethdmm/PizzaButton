import wsgiref.handlers
import operator
import datetime
from django.utils import simplejson as json
from google.appengine.api import mail
from google.appengine.ext import db
from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from google.appengine.ext.webapp import template
from google.appengine.ext.webapp.util import run_wsgi_app

class Address(db.Model):
  number = db.StringProperty(required=True)
  street = db.StringProperty(required=True)
  city = db.StringProperty(required=True)
  postalCode = db.StringProperty(required=True)
  province = db.StringProperty(required=True)

class Order(db.Model):
  qty = db.IntegerProperty(required=True)
  size = db.IntegerProperty(required=True)
  pizza_type = db.IntegerProperty(required=True)

class User(db.Model):
  name = db.StringProperty(required=True)
  phoneNumber = db.PhoneNumberProperty(required=True)
  email = db.EmailProperty(required=True)
  addressId = db.ReferenceProperty(Address, 
    collection_name="user_address_set",
    required=True)
  orderId = db.ReferenceProperty(Order, 
    collection_name="user_order_set",
    required=True) 
  
class OrderHistory(db.Model):
  user = db.IntegerProperty(required=True)
  time = db.DateTimeProperty(auto_now_add=True)
  
class Pizza(db.Model):
  pizzaType = db.StringProperty(required=True)
  # fill out later
