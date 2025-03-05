# Définition des variables
SRC_DIR = src
BIN_DIR = bin
JAVAC = javac
JAVA = java
RM = rm -f

# Assure que le dossier bin existe avant de compiler
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Compiler tous les fichiers Java et placer les .class dans bin/
all: $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) $(shell find $(SRC_DIR) -name "*.java")

# Compiler un fichier spécifique avec autocomplétion
compile:
	@echo "Sélectionnez un fichier Java à compiler :"
	@select file in $(shell find $(SRC_DIR) -name "*.java" 2>/dev/null); do \
		$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) $$file; \
		break; \
	done

# Exécuter une classe spécifique avec autocomplétion
run:
	@echo "Sélectionnez une classe à exécuter :"
	@select class in $(shell find $(BIN_DIR) -name "*.class" | sed 's|$(BIN_DIR)/||' | sed 's|.class||' | sed 's|/|.|g'); do \
		$(JAVA) -cp $(BIN_DIR) $$class; \
		break; \
	done

# Exécuter la démo principale
run-demo:
	$(JAVA) -cp $(BIN_DIR) demos.DemoLauncher

# Supprimer un fichier .class spécifique avec autocomplétion
clean-file:
	@echo "Sélectionnez un fichier .class à supprimer :"
	@select file in $(shell find $(BIN_DIR) -name "*.class" 2>/dev/null); do \
		$(RM) $$file; \
		break; \
	done

# Supprimer tous les fichiers compilés
clean:
	$(RM) $(shell find $(BIN_DIR) -name "*.class")
