FROM maven:3.9.6-eclipse-temurin-21

# Installer make, unzip, wget
RUN apt-get update && apt-get install -y --no-install-recommends \
  make unzip wget \
  && rm -rf /var/lib/apt/lists/*

# Ajouter Kotlin
ENV KOTLIN_VERSION=2.1.21
RUN wget https://github.com/JetBrains/kotlin/releases/download/v$KOTLIN_VERSION/kotlin-compiler-$KOTLIN_VERSION.zip \
  && unzip kotlin-compiler-$KOTLIN_VERSION.zip -d /opt \
  && rm kotlin-compiler-$KOTLIN_VERSION.zip

# Ajouter le compilateur Kotlin au PATH
ENV PATH="/opt/kotlinc/bin:$PATH"

# Vérification des outils (facultatif mais utile)
RUN java -version && kotlinc -version && mvn -version && make --version
