# just a test file to get all the users in the db

from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from model_wrapper import user_list

class UsersHandler(webapp.RequestHandler):
  def get(self):
    self.response.out.write(user_list(self))

def main():
  application = webapp.WSGIApplication([('/get_users',UsersHandler)], debug=True)
  util.run_wsgi_app(application)
  
if __name__ == '__main__':
  main()
