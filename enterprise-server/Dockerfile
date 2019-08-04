#
# Aerospike Server Enterprise Edition Dockerfile
#
# http://github.com/aerospike/aerospike-server-enterprise.docker
#

FROM ubuntu:16.04

# Work from /tmp
WORKDIR /tmp

# Build environment vars
ARG AEROSPIKE_USER_NAME="${AEROSPIKE_USER_NAME}"
ARG AEROSPIKE_PASSWORD="${AEROSPIKE_PASSWORD}"

# Install Aerospike
RUN \
  apt-get update -y \
  && apt-get install -y wget python python-argparse python-bcrypt python-openssl logrotate net-tools iproute2 iputils-ping gettext-base \ 
  && wget "https://www.aerospike.com/enterprise/download/server/4.5.3.4/artifact/ubuntu16" -O aerospike-server.tgz --user=${AEROSPIKE_USER_NAME} --password=${AEROSPIKE_PASSWORD} \
  && mkdir aerospike \
  && tar xzf aerospike-server.tgz --strip-components=1 -C aerospike \
  && dpkg -i aerospike/aerospike-server-enterprise-4.5.3.4.ubuntu16.04.x86_64.deb \
  && dpkg -i aerospike/aerospike-tools-3.19.0.ubuntu16.04.x86_64.deb \
  && mkdir -p /var/log/aerospike/ \
  && mkdir -p /var/run/aerospike/ \
  && rm -rf aerospike-server.tgz aerospike /var/lib/apt/lists/* \
  && rm -rf /opt/aerospike/lib/java \
  && dpkg -r wget ca-certificates openssl xz-utils\
  && dpkg --purge wget ca-certificates openssl xz-utils\
  && apt-get purge -y \
  && apt autoremove -y 

RUN \
   apt-get update -y \
   && apt-get install ldap-utils -y 


# Add the Aerospike configuration specific to this dockerfile
COPY aerospike.template.conf /etc/aerospike/aerospike.template.conf
COPY entrypoint.sh /entrypoint.sh

# Mount the Aerospike data directory
VOLUME ["/opt/aerospike/data"]

# Expose Aerospike ports
#
#   3000 – service port, for client connections
#   3001 – fabric port, for cluster communication
#   3002 – mesh port, for cluster heartbeat
#   3003 – info port
EXPOSE 3000 3001 3002 3003 

# Execute the run script
# We use the `ENTRYPOINT` because it allows us to forward additional
# arguments to `aerospike`
# Execute the run script in foreground mode
ENTRYPOINT ["/entrypoint.sh"]
CMD ["asd"]
