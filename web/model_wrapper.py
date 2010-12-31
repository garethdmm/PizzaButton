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

from model import Address
from model import Order
from model import User

 # Takes in a user json string and adds a user, address and order to the database
 # Returns a user key
def insert_user(user_json):
  #print "INSERT USER"
  #print user_json
 
  user_string = json.loads(user_json)
  userAddress = Address(number = user_string["number"],
                    street = user_string["street"],
                    postalCode = user_string["postalCode"],
                    city = user_string["city"],
                    province = user_string["province"])

  #print "pizzaId: " + user_string["pizzaId"]
  
  userOrder = Order(qty = int(user_string["quantity"]),
								size = int(user_string["pizzaSize"]),
                pizza_type = int(user_string["pizzaId"]))   
  userAddress.put()
  userOrder.put()
  user = User(name = user_string["name"],
              phoneNumber = user_string["phoneNumber"],
              email = user_string["email"],
              addressId = userAddress.key(), 
              orderId = userOrder.key())  

  user.put()
  return user.key();
  
#Returns a =list of avaiable pizza types   
def pizza_list(self):
  results = db.GqlQuery("SELECT * from Pizza")
  temp=[]
  for result in results :
     temp.append(result.pizzaType)     
     
  pizzas_json = json.dumps(temp, separators=(',',':'))
  #self.response.out.write(pizzas_json)

  self.response.out.write("""{"1": "pepperoni"}""")

def user_list(self):
  results = db.GqlQuery("SELECT * from User")
  users=[]
  for result in results:
    users.append(result.name)

  users_json = json.dumps(users, separators=(',',':'))

  self.response.out.write(users_json)
