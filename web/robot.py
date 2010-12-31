# robot.py - a robot that brings you pizza
# Copyright 2010 - 7cubed
#
# @author gmacleod

import mechanize

def getPizza():
  return "Ordering a Pizza, I swear!"

  # Gilbert & Barbara's code
 
  #br = mechanize.Browser()
  #br.set_handle_equiv(False)
  #br.set_handle_robots(False)
  #br.open("https://order.pizzanova.com/fcgi-bin/Weborder.pl")
  #br.select_form(nr=0)

  #br["email"] = "7cubedproject@gmail.com"
  #br["password"] = "velocity"
  #br.submit()
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0))  #delivery

  #br.select_form(nr=0)
  #br.open(br.click(nr=1))  #re-enter location
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br["postalcode"] = "N2L 2B5"
  #br.find_control("LOCATIONTYPE").get("House").selected = True
  #br.submit()
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.submit()
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br["streetno"] = "17"
  #br["phoneno"] = "2268682698"
  #br.submit()
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0))  # deliver (instead of re-enter)
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0))  # order a pizza;
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.find_control("PIZZASIZE").get("Small").selected = True
  #br.find_control("CODE03").get("On Whole Pizza").selected = True
  #br.open(br.click(type="submit", nr=0))  # add to order
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(type="submit", nr=6)) #proceed to chekout
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(type="submit", nr=1)) #proceed to payment
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0)) # by cash
  #self.response.out.write(br.response().get_data())

  #br.select_form(nr=0)
  #br.open(br.click(nr=0)) # submit
