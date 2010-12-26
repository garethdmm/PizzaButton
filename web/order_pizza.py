from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from model import pizza_list

class PizzaHandler(webapp.RequestHandler):
	def get(self):
  #TODO: hook this up with the mechanize code
  self.response.out.write("You're ordering a pizza!");

def main():
  application = webapp.WSGIApplication([('/pizza_list',PizzaHandler)], debug=True)
  util.run_wsgi_app(application)
  
if __name__ == '__main__':
  main()
