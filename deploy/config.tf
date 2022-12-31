provider "docker" {
  version = "~> 2.24.0"
  host = "tcp://65.1.106.222/"
  
registry_auth {
    address = "3.109.122.168:8083"
    config_file = "${pathexpand("~/.docker/config.json")}"
  }
}

#resource "docker_service" "book_appointment" {
#  name = "book_appointment"
#
#  task_spec {
#    container_spec {
#      image = "65.1.106.222/teleprod/book_appointment:${var.Version}"
#      env = {
#        active_profile_env= "prod"
#        spring_config_url_env= "http://65.1.106.222/config"
#        spring_config_label_env= "1.0.0"
#        }
#      mounts {
#        target    = "/var/telemedicine/documents"
#        source    = "/datafs/telemedicine/documents"
#        type      = "bind"      
#      }
#      mounts {
#        target    = "/etc/TZ"
#        source    = "/etc/localtime"
#        type      = "bind"
#      }
#  }
#  }
#}
#}
#update_config {
#  parallelism       = 1
#  delay             = "10s"
#  failure_action    = "pause"
#  monitor           = "5s"
#  max_failure_ratio = "0.1"
#  order             = "start-first"
#}
#
#endpoint_spec {
#  ports {
#    target_port = "7079"
#    published_port = "7079"
#     }
#}

