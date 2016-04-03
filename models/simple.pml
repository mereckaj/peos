process simple {
  action a {
    requires { foo }
    provides { foo } 
    agent { "http://www.pc.com/test"}
  }
  action b {
    requires { foo }
    provides { bar } 
  }
}  
