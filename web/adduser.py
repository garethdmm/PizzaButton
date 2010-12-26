from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from model import insert_user

class AddUserHandler(webapp.RequestHandler):
	def put(self):
	  user = self.request.get('user')
	  if (user != nil) : 
	    user_id = insert_user(user)
	    self.response.out.write(user)
	  
	  
def main():
  application = webapp.WSGIApplication([('/addUser',AddUserHandler)], debug=True)
  util.run_wsgi_app(application)
  
if __name__ == '__main__':
  main()