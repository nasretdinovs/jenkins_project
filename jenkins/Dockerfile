FROM jenkins/jenkins:2.479.3-jdk17
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
# Установка Allure
RUN curl -L -o allure-2.32.0.zip https://github.com/allure-framework/allure2/releases/download/2.32.0/allure-2.32.0.zip \
    && unzip allure-2.32.0.zip -d /usr/local/ \
    && rm allure-2.32.0.zip
ENV PATH="/usr/local/allure-2.32.0/bin:${PATH}"
#USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"