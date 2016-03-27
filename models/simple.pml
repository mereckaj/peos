process simple {
  action a {
    requires { foo }
    provides { foo } 
    agent { poo }
  }
  action b {
    requires { foo }
    provides { bar } 
  }
}  
