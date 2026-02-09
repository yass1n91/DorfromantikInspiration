# Variables
PROJECT_NAME = dorfmantik
SRC_DIR = src
OUT_DIR = build
RES_DIR = res
PACKAGE = fr/iutfbleau/dorfmantik
MAIN_CLASS = fr.iutfbleau.dorfmantik.Lancement

# Compilation des fichiers Java
compile:
	javac -d $(OUT_DIR) -cp "$(RES_DIR)/mariadb-java-client-2.5.3.jar" \
		$(SRC_DIR)/$(PACKAGE)/*/*.java \
		$(SRC_DIR)/$(PACKAGE)/*/*.java \
		$(SRC_DIR)/$(PACKAGE)/*.java

# Copie des ressources
cp_resources: compile
	# Création des répertoires nécessaires dans le dossier build
	mkdir -p $(OUT_DIR)/fonts $(OUT_DIR)/musique $(OUT_DIR)/images $(OUT_DIR)/META-INF $(OUT_DIR)/_LIB

	# Copie des polices
	cp $(RES_DIR)/fonts/* $(OUT_DIR)/fonts/

	# Copie des fichiers audio
	cp $(RES_DIR)/musique/* $(OUT_DIR)/musique/

	# Copie des images
	cp $(RES_DIR)/*.png $(OUT_DIR)/images/
	cp $(RES_DIR)/*.jpg $(OUT_DIR)/images/

	# Copie des fichiers META-INF (ex. MANIFEST.MF)
	cp $(RES_DIR)/META-INF/* $(OUT_DIR)/META-INF/

	# Copie du pilote MariaDB
	cp $(RES_DIR)/mariadb-java-client-2.5.3.jar $(OUT_DIR)/_LIB/

# Création du fichier JAR
jar: cp_resources
	jar cfm $(PROJECT_NAME).jar $(RES_DIR)/META-INF/MANIFEST.MF -C $(OUT_DIR) .

# Exécution de l'application
run: jar
	java -cp "$(PROJECT_NAME).jar:$(RES_DIR)/mariadb-java-client-2.5.3.jar" $(MAIN_CLASS)

# Nettoyage des fichiers générés
clean:
	rm -rf $(OUT_DIR)/*
	rm -f $(PROJECT_NAME).jar
	@echo "Fichiers de compilation et JAR supprimés."



# # Variables
# PROJECT_NAME = dorfmantik
# SRC_DIR = src
# BUILD_DIR = build
# RES_DIR = res

# # Récupération des fichiers java dans src
# JAVA_FILES = $(shell find $(SRC_DIR)/fr/iutfbleau/dorfmantik -name "*.java")

# # Nom du fichier jar
# JAR_FILE = $(PROJECT_NAME).jar

# # Classe principale
# MAIN_CLASS = fr.iutfbleau.dorfmantik.Lancement

# # Options de compilation
# ifeq ($(OS),Windows_NT)
#     JAVAC_FLAG_CP = -cp ".;$(RES_DIR)/mariadb-java-client"
# else
#     JAVAC_FLAG_CP = -cp ".:$(RES_DIR)/mariadb-java-client"
# endif
# JAVAC_FLAGS = -d $(BUILD_DIR) $(JAVAC_FLAG_CP)

# # Cible par défaut
# .PHONY: all
# all: clean compile jar run

# # Compilation
# .PHONY: compile
# compile: create_dirs
# 	javac $(JAVAC_FLAGS) -sourcepath $(SRC_DIR) $(JAVA_FILES)

# # Création du JAR
# .PHONY: jar
# jar: compile
# 	jar cfm $(JAR_FILE) $(RES_DIR)/META-INF/MANIFEST.MF -C $(BUILD_DIR) . -C $(RES_DIR) .

# # Créer les répertoires nécessaires
# .PHONY: create_dirs
# create_dirs:
# 	mkdir -p $(BUILD_DIR) $(BUILD_DIR)

# # Exécution du fichier JAR
# .PHONY: run
# run: jar
# 	java -cp "$(PROJECT_NAME).jar:mariadb-java-client-2.5.3.jar" $(MAIN_CLASS)


# # Nettoyage
# .PHONY: clean
# clean:
# 	rm -rf $(BUILD_DIR)/*
# 	rm -f $(JAR_FILE)
# 	@echo "Fichiers compilés nettoyés."
