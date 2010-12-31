from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from robot import getPizza

class OrderPizzaHandler(webapp.RequestHandler):
  def post(self):
    #TODO: hook this up with the mechanize code
    userId = self.request.get('userId')
    self.response.out.write("Ordering pizza for user: " + userId)

def main():
  application = webapp.WSGIApplication([('/order_pizza',OrderPizzaHandler)], debug=True)
  util.run_wsgi_app(application)
  
if __name__ == '__main__':
  main()
