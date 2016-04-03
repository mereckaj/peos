process swimlane_test{
    action AliceOnly{
        agent { Alice}
        requires { alice_resource }
        provides { alice_resource }
    }
    action BobOnly {
        agent {Bob}
        requires { alice_resource }
        provides { bob_resource }
    }
    action CharlieOnly{
        agent {Charlie}
        requires {bob_resource}
        provides {charlie_resouce}
    }
    action AliceBob{
        agent {Alice}
        agent {Bob}
        requires {charlie_resource}
        provides {ab_resource}
    }
    action BobCharlie{
        agent {Charlie}
        agent {Bob}
        requires {charlie_resource}
        provides {bc_resource}
    }
    action AliceCharlie{
        agent {Alice}
        agent {Charlie}
        requires {alice_resource}
        provides {ac_resource}
    }
    action Everyone{
        agent {Alice}
        agent {Bob}
        agent {Charlie}
    }
    
}
