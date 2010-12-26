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

class User(db.Model):
  name = db.StringProperty(required=True)
  phoneNumber = db.PhoneNumberProperty(required=True)
  email = db.EmailProperty(required=True)
  addressId = db.IntegerProperty(required=True)
  orderId = db.IntegerProperty(required=True) 
  
class Address(db.Model):
  number = db.StringProperty(required=True)
  street = db.StringProperty(required=True)
  city = db.StringProperty(required=True)
  postalCode = db.StringProperty(required=True)
  province = db.StringProperty(required=True)
  
class OrderHistory(db.Model):
  user = db.IntegerProperty(required=True)
  time = db.DateTimeProperty(auto_now_add=True)
  
class Order(db.Model):
  qty = db.IntegerProperty(required=True)
  size = db.IntegerProperty(required=True)
  pizza_type = db.IntegerProperty(required=True)
  
class Pizza(db.Model):
  pizzaType = db.StringProperty(required=True)
  # fill out later
  
 
 # Takes in a user json string and adds a user, address and order to the database
 # Returns a user key
def insert_user(user_json):
  user_string = json.loads(user_json)
  address = Address(number = user_string["number"],
                    street = user_string["street"],
                    city = user_string["postalCode"],
                    province = user_string["province"])

  order = Order(qty = user_string["quantity"],
								size = user_string["pizzaSize"],
                pizza_type = user_string["pizzaId"])   
  address.put()
  order.put()
  user = User(name = user_string["name"],
              phoneNumber = user_string["phoneNumber"],
              email = user_string["email"],
              address = key(address), 
              order = key(order))  

  user.put()
  return user.key();
  
#Returns a =list of avaiable pizza types   
def pizza_list(self) :
  results = db.GqlQuery("SELECT * from Pizza")
  temp=[]
  for result in results :
     temp.append(result.pizzaType)     
     
  pizzas_json = json.dumps(temp, separators=(',',':'))
  self.response.out.write(pizzas_json)
