#!/usr/bin/env python
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from model import pizza_list
from model import Pizza

# here's a comment to test git

class MainHandler(webapp.RequestHandler):
    def get(self):
        pizza_list(self);
        import mechanize
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

def main():
    application = webapp.WSGIApplication([('/', MainHandler)],
                                         debug=True)
    util.run_wsgi_app(application)


if __name__ == '__main__':
    main()
