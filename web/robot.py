# robot.py - a robot that brings you pizza
# Copyright 2010 - present, 7cubed
#
# @author bemacdo, gilbertleung, gmacleod

from model import User
from model_wrapper import getUser
from model_wrapper import getAddress

import mechanize

def getPizza(someObject, userId):

  user = getUser(userId)
  address = user.addressId

  # Gilbert & Barbara's code

  br = mechanize.Browser()
  br.set_handle_equiv(False)
  br.set_handle_robots(False)
  br.open("https://order.pizzanova.com/fcgi-bin/Weborder.pl")
  br.select_form(nr=0)

  br["email"] = "7cubedproject@gmail.com"
  br["password"] = "velocity"
  br.submit()
  #someObject.response.out.write("Output: " + br.response().get_data())
  #print "Output: " + br.response().get_data()

  br.select_form(nr=0)
  br.open(br.click(nr=0))  #delivery

  br.select_form(nr=0)
  br.open(br.click(nr=1))  #re-enter location
  #someObject.response.out.write(br.response().get_data())

  print "Postal Code: " + address.postalCode
  br.select_form(nr=0)
  br["postalcode"] = address.postalCode
  # TODO: make housing type customizable
  br.find_control("LOCATIONTYPE").get("House").selected = True
  br.submit()
  #someObject.response.out.write(br.response().get_data())

  br.select_form(nr=0)
  br.submit()
  #someObject.response.out.write(br.response().get_data())

  print "Number: " + address.number
  print "Phone number: " + user.phoneNumber

  br.select_form(nr=0)
  br["streetno"] = address.number
  br["phoneno"] = user.phoneNumber
  br.submit()
  #someObject.response.out.write(br.response().get_data())

  # TODO: Add checking that the street name it got is correct
  br.select_form(nr=0)
  br.open(br.click(nr=0))  # deliver (instead of re-enter)
  #self.response.out.write(br.response().get_data())

  br.select_form(nr=0)
  br.open(br.click(nr=0))  # order a pizza;
  #someObject.response.out.write(br.response().get_data())

  # TODO: make pizza order customizable
  br.select_form(nr=0)
  br.find_control("PIZZASIZE").get("Small").selected = True
  br.find_control("CODE03").get("On Whole Pizza").selected = True
  br.open(br.click(type="submit", nr=0))  # add to order
  #self.response.out.write(br.response().get_data())

  br.select_form(nr=0)
  br.open(br.click(type="submit", nr=6)) #proceed to chekout
  # someObject.response.out.write(br.response().get_data())

  br.select_form(nr=0)
  br.open(br.click(type="submit", nr=1)) #proceed to payment
  #someObject.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0)) # by cash
  #someObject.response.out.write(br.response().get_data())

  someObject.response.out.write("SUCCESS!")

  #DO NOT UNCOMMENT UNLESS YOU WANT TO ACTUALLY ORDER A PIZZA

  #br.select_form(nr=0)
  #br.open(br.click(nr=0)) # submit
