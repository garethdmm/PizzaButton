from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
#import model
from model_wrapper import insert_user

class AddUserHandler(webapp.RequestHandler):
  def post(self):
    
    user = self.request.get('user')
    if (user != None): 
      user_id = insert_user(user)
      self.response.out.write(user_id)
    else:
      self.response.out.write("Invalid user!")
	  
	  
def main():
  application = webapp.WSGIApplication([('/add_user',AddUserHandler)], debug=True)
  util.run_wsgi_app(application)
  
if __name__ == '__main__':
  main()
