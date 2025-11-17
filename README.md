# 🚀Multi-Builder premium

**Builder nouvelle génération** avec chiffrement de ressources, protection bytecode, obfuscation intelligente et prise en charge Python. 
**Windows maintenant, macOS et Linux en développement.**

---

### 💝 Soutenir le projet

Si ce builder vous a aidé, merci de soutenir le développement :

[![Lyggos](https://pay.lygosapp.com/$etsmeta)]
[![En savoir plus](https://img.shields.io/badge/Sponsoring-Details-4CAF50?style=for-the-badge)](SUPPORT.md)

Votre soutien permet de:
- ✅ Développer de nouvelles fonctionnalités
- ✅ Supporter macOS et Linux
- ✅ Améliorer la documentation
- ✅ Corriger les bugs rapidement

---

Constructeur professionnel pour applications Electron, Python exigeant une **sécurité maximale**, des **builds reproductibles** et une **customisation totale**. avec des outils embarqués dans le dossier.

---

## ⭐ Vous aimez ce projet ?

N'hésitez pas à:
- ⭐ **Mettre une star** sur GitHub
- 🍴 **Forker** le projet
- 🐛 **Signaler des bugs**
- 💡 **Proposer des améliorations**
- 💰 **[Soutenir financièrement](#-soutenir-le-projet)** le développement

---

## 🚀 Installation
Toutes les dépendances essentielles pour les projets electron sont embarquées pour une construction offline !
- NSIS : Inclus dans `build_tools/vendor/nsis/`
- UPX : Inclus dans `build_tools/vendor/upx/`
- 7-Zip : Inclus dans `build_tools/vendor/7zip-bin/`
- Tous les modules npm : Inclus dans `build_tools/vendor/`

### Dépendances minimales
Seul `electron@^39.1.1` et Python resent est requis pour le développement. Installez-le une seule fois :
```powershell
npm install
```
### Architecture des modules embarqués
```
build_tools/vendor/
  ├── asar/                    # Packaging et archivage
  ├── bytenode/                # Compilation JavaScript en bytecode V8
  ├── electron-asar/           # Assar officiel Electron
  ├── electron-packager/       # Empaquetage Electron
  ├── javascript-obfuscator/   # Obfuscation de code JS
  ├── minimist/                # Parsing d'arguments CLI
  ├── rcedit/                  # Édition des ressources Windows
  ├── sharp/                   # Traitement d'images
  ├── tmp/                     # Gestion des fichiers temporaires
  ├── 7zip-bin/                # Compression 7-Zip
  ├── nsis/                    # Créateur d'installateurs
  ├── upx/                     # Compression d'exécutables
  └── signtool/                # Signature de code Windows (optionnel)
```

Chaque module est utilisé localement sans dépendre du `node_modules` global, garantissant une **reproductibilité totale et une indépendance réseau**.

### Signature de code
Le builder intègre un système de signature complet avec deux options :

1. **Certificat auto-signé (par défaut)**
   - Généré automatiquement lors du premier build
   - Stocké dans `build_tools/certs/cert-[hash].pfx`
   - Mot de passe stocké dans `build_tools/certs/cert-[hash].key`
   - Adapté pour les tests et le développement

2. **Certificat personnalisé (recommandé pour la production)**
   - Nécessite Windows SDK (pour signtool.exe) - détecté automatiquement
   - Placer votre certificat .pfx dans `build_tools/certs/signing.pfx`
   - Configurer via les variables d'environnement :
     ```powershell
     $env:PFX_PATH="chemin/vers/certificat.pfx"  # Optionnel si dans certs/signing.pfx
     $env:PFX_PASS="mot-de-passe-certificat"
     ```

### Comportement par défaut de la signature
- La signature est automatique par défaut : le builder tentera toujours de signer les binaires générés.
- Par conception pour les builds offline et reproductibles, le builder utilise en priorité le binaire `signtool.exe` fourni localement : placez-le dans `build_tools/vendor/signtool/signtool.exe` si vous souhaitez contrôler la version de l'outil.
- Si vous fournissez un module `build_tools/signing.js` exposant une fonction `signExe(path, cert, pass)`, le builder l'utilisera en priorité pour effectuer la signature.
- Si aucun outil ni module n'est présent, le builder génèrera automatiquement un certificat auto-signé (usage développement) et tentera la signature avec celui-ci.

### Détection dynamique d'Electron
Le builder détecte automatiquement la distribution Electron aux emplacements suivants :
1. `./node_modules/electron/dist` (local au projet)
2. `../node_modules/electron/dist` (dossier parent)
3. `../../node_modules/electron/dist` (racine du workspace)

Cela permet une **flexibilité maximale** : vous pouvez partager une installation Electron commune ou avoir une instance isolée par projet.

---

## 📦 Gestion des Dépendances - Installation et Utilisation

### 🎯 Concept Global

Le système de dépendances du builder fonctionne selon deux niveaux :

1. **Dépendances Electron (npm)** : Pour votre application JavaScript/Electron
2. **Dépendances Python** : Pour les applications Python créées avec le builder Python

#### Architecture Générale

```
Applications Electron           Applications Python
        ↓                               ↓
  package.json              config.py + requirements.txt
        ↓                               ↓
   npm install              pip install (Python Embeddable)
        ↓                               ↓
  node_modules/             Python site-packages/
```

---

## 📌 Installation de Dépendances Electron (JavaScript/Node.js)

### 1️⃣ Ajouter une Dépendance à `package.json`

**Étape 1 : Localiser votre `package.json`**
```powershell
# Depuis votre dossier d'application
ls package.json
```

**Étape 2 : Ajouter une dépendance manuelle (édition directe)**

Ouvrez `package.json` et ajoutez dans la section `"dependencies"` :

```json
{
  "name": "mon-app-electron",
  "version": "1.0.0",
  "main": "main.js",
  "dependencies": {
    "electron-store": "^8.1.0",    // ← Nouvelle dépendance
    "uuid": "^9.0.0",              // ← Nouvelle dépendance
    "axios": "^1.4.0"              // ← Nouvelle dépendance
  }
}
```

**Étape 3 : Installer les dépendances**
```powershell
# Depuis votre dossier d'application
npm install
```

✅ **Résultat :** Tous les packages sont téléchargés dans `node_modules/`

### 2️⃣ Installer via npm directement (Méthode Rapide)

```powershell
# Depuis votre dossier d'application
npm install electron-store uuid axios
```

Cela ajoute automatiquement les dépendances à `package.json` et les installe.

### 3️⃣ Utiliser une Dépendance dans votre Code

Une fois installée, vous pouvez l'importer et l'utiliser :

**Exemple 1 : Utiliser `electron-store` (Stockage persistant)**

```javascript
// main.js ou n'importe quel fichier JavaScript
const Store = require('electron-store');

const store = new Store();

// Stocker une valeur
store.set('user', {
  name: 'Jean',
  email: 'jean@example.com'
});

// Récupérer une valeur
const user = store.get('user');
console.log(user); // { name: 'Jean', email: 'jean@example.com' }
```

**Exemple 2 : Utiliser `uuid` (Générer des IDs uniques)**

```javascript
// renderer.js ou n'importe quel fichier JavaScript
const { v4: uuidv4 } = require('uuid');

// Générer un ID unique
const userId = uuidv4();
console.log(userId); // ex: "550e8400-e29b-41d4-a716-446655440000"
```

**Exemple 3 : Utiliser `axios` (Requêtes HTTP)**

```javascript
// Faire une requête HTTP
const axios = require('axios');

axios.get('https://api.example.com/data')
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error('Erreur:', error);
  });
```

### 4️⃣ Types de Dépendances

| Type | Commande | Description | Utilisation |
|------|----------|-------------|------------|
| **Production** | `npm install package-name` | Code nécessaire à l'exécution | Inclus dans l'app |
| **Développement** | `npm install --save-dev package-name` | Outils de build seulement | Exclu de l'app |

## ✅ Bonnes Pratiques

### 1. Toujours Versionner les Dépendances
```
✅ BON:      package>=1.0.0
❌ MAUVAIS:  package (version flottante)
```

### 2. Tester Localement Avant de Packager
```powershell
# Python
 .\build_tools\vendor\python_embeddable\python.exe -m pip install -r requirements.txt
python __main__.py

# Node/Electron
npm install
npm start
```

### 3. Utiliser `requirements.txt` pour Python
```
# ✅ BON - Fichier requirements.txt
requests==2.31.0
numpy>=1.24.0

# ❌ MAUVAIS - Pas de fichier requirements.txt
```

### 4. Documenter les Dépendances
```python
# En haut de chaque fichier
"""
Dépendances requises:
- requests: pour les appels HTTP
- numpy: pour les calculs numériques
"""
import requests
import numpy as np
```

---

## 🚀 Mise à Jour des Dépendances

### Python

```powershell
# Voir les dépendances qui peuvent être mises à jour
 .\build_tools\vendor\python_embeddable\python.exe -m pip  list --outdated

# Mettre à jour une dépendance
 .\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade requests

# Mettre à jour tous les packages
 .\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade -r requirements.txt
```

### Node/Electron

```powershell
# Voir les versions disponibles
npm outdated

# Mettre à jour une dépendance
npm update electron-store

# Mettre à jour tout
npm update
```

---

## Utilisation

### 🚀 Démarrage rapide - Mode par défaut
**Le plus simple** - Exécutez le builder depuis votre dossier d'application :
```powershell
# Depuis votre répertoire d'application
cd D:\mon-app
node D:\chemin-vers\metadidomi-builder\build_tools\builder.js
```

**OU depuis app_src inclus :**
```powershell
# Exemple avec app_src du builder (simplement tester)
cd D:\metadidomi-builder\app_src
node ../build_tools/builder.js
```

✅ **Résultat :**
- Si aucun fichier d'app : Génère `package.json`, `main.js`, `preload.js`, `index.html` à la racine
- Exécutable et installateur créés dans `./dist/`
- `./dist/MonApp-Setup-1.0.0.exe` (installateur professionnel)

### 📦 Mode professionnel - Packager votre projet existant
**Avec votre propre application Electron** (✅ RECOMMANDÉ) :
```powershell
# IMPORTANT: Se positionner dans le répertoire de VOTRE APPLICATION
# Ne pas lancer depuis le répertoire du builder !

# Exemple 1: Depuis votre application
cd C:\MonApp
node C:\metadidomi-builder\build_tools\builder.js

# Exemple 2: Depuis app_src du builder (test/demo)
cd C:\metadidomi-builder\app_src
node ../build_tools/builder.js

# Exemple 3: Avec chemins personnalisés
node C:\metadidomi-builder\build_tools\builder.js --app-src D:\mon-app --output D:\dist
```

✅ **Résultat :** `./dist/MonApp-Setup-1.0.0.exe` (installateur)

### 🔐 Avec chiffrement
Définissez une clé de chiffrement pour protéger les ressources :
```powershell
cd D:\mon-app
$env:KEY="votre-clé-secrète"
node D:\chemin-vers\metadidomi-builder\build_tools\builder.js
```

**⚠️ IMPORTANT :** 
- Les paramètres `--app-src` et `--output` sont **optionnels**
- **Comportement par défaut :** lit depuis le répertoire courant et génère dans `./dist`
- Si aucun fichier d'app : Les fichiers de démo sont générés à la racine du répertoire courant
- Le builder **n'écrase pas** les fichiers existants
- Exclusion automatique : `node_modules`, `.git`, `dist`, `build`, `.next`, `coverage`
- Votre projet doit avoir au minimum : `package.json`, `main.js` (sinon ils seront générés)

### Temps de construction
- Application par défaut : environ 2-3 minutes
- Le temps varie selon la taille de l'application et les options activées (UPX, LITE, CREATE_INSTALLER, etc.)
- LITE mode : peut être légèrement plus lent (analyse des dépendances)

## Structure minimale de l'application
Le builder lit depuis le répertoire courant par défaut. Voici une structure recommandée :

```
mon-app/
  package.json        # nom, productName, version, main (⭐ généré si manquant)
  main.js             # processus principal Electron (⭐ généré si manquant)
  preload.js          # script preload (⭐ généré si manquant)
  index.html          # interface principale (⭐ généré si manquant)
  assets/
    icon.ico          # icône Windows (optionnelle)
    js/
      renderer.js     # scripts front-end
```

### ⭐ Auto-génération intelligente

Si vous exécutez le builder dans un dossier vide, il génère automatiquement :
- ✅ `package.json` : Configuration de base
- ✅ `main.js` : Application Electron fonctionnelle
- ✅ `preload.js` : Bridge sécurisé contextBridge
- ✅ `index.html` : Interface de démonstration
- ✅ `assets/` : Dossier pour vos ressources

Ensuite, vous pouvez éditer ces fichiers et relancer le builder pour construire votre application.

### Configurations minimales :

**`package.json`** : au minimum
```json
{
  "name": "mon-app-electron",
  "productName": "MonApp",
  "version": "1.0.0",
  "main": "main.js"
}
```

**`main.js`** : créez une BrowserWindow et chargez `index.html`
- Le builder génère un exemple si absent

**`preload.js`** : expose uniquement les API nécessaires via `contextBridge` pour respecter `contextIsolation`
- Le builder génère un exemple si absent

**`index.html`** : page d'entrée simple
- Le builder génère un exemple si absent

**`assets/icon.ico`** : si présente, elle sera utilisée pour l'exécutable et l'installateur

### Modes de construction

#### ⭐ Standard (par défaut) - Installateur NSIS
Crée un installateur professionnel avec options d'installation :
```powershell
node build_tools/builder.js
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

#### 💾 Portable - Exécutable sans installation
Crée un exécutable portable au lieu d'un installateur :
```powershell
$env:CREATE_PORTABLE_EXE="true"
node build_tools/builder.js
# Résultat: ./dist/MonApp.exe (portable, ~130MB avec Electron)
```

#### ⚡ LITE - Mode optimisé
Analyse et exclut les modules inutiles pour un build plus léger :
```powershell
$env:LITE_BUILD="true"
node build_tools/builder.js
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (optimisé)
# Rapport: electron-lite-deps-report.txt
```

#### 🚫 Sans installateur
Crée uniquement les fichiers de base sans NSIS :
```powershell
$env:SKIP_INSTALLER="true"
node build_tools/builder.js
# Résultat: Ressources de base seulement (win-unpacked)
```

#### 🔗 Combinaisons utiles
```powershell
# Portable + LITE
$env:CREATE_PORTABLE_EXE="true"
$env:LITE_BUILD="true"
node build_tools/builder.js

# Avec clé de chiffrement + Sortie personnalisée
$env:KEY="ma-clé-secrète"
node build_tools/builder.js --output C:\MonApp-dist

# Toutes les options
$env:KEY="clé"
$env:LITE_BUILD="true"
$env:USE_UPX="true"
node build_tools/builder.js --app-src D:\app --output D:\dist
```

### Signature de code (optionnel)
La signature est automatique si configurée :
```powershell
$env:PFX_PATH="chemin/vers/certificat.pfx"  # Optionnel si dans certs/signing.pfx
$env:PFX_PASS="mot-de-passe-certificat"
```

## Résumé des fichiers générés
- `dist/MonApp-Setup-X.X.X.exe` : Installateur NSIS professionnel (créé par défaut)
- `dist/MonApp.exe` ou `dist/MonApp-lite.exe` : Exécutable portable (si `CREATE_PORTABLE_EXE=true`)
- `electron-lite-deps-report.txt` : Rapport d'optimisation LITE (si `LITE_BUILD=true`)

## Fonctionnalités
- ✨ Construction automatisée d'applications Electron
- 📊 Mode LITE pour optimisation automatique des dépendances
- � Rapport détaillé des modules inclus/exclus
- 🔒 Chiffrement des ressources (AES-256-CBC)
- 📦 Compression multi-niveau avec UPX
- 🧪 Test automatique post-compression
- ✍️ Signature de code automatique (optionnel)
- 💼 Création d'exécutable portable
- 🎯 Installateur NSIS professionnel
- 🛡️ Protection hybride du code (bytecode + obfuscation légère)
- 🔌 Construction 100% offline
- 🔍 Validation HMAC des ressources
- 📝 Watermarking des builds
- 🔄 Téléchargement et installation automatiques de NSIS
- 🎨 Interface d'installation personnalisable
- 🚀 Options de démarrage automatique
- 🗑️ Désinstallation propre et complète
- 🛡️ Protection renforcée du code par compilation bytecode
- ⚡ Système de fallback automatique pour la compatibilité
- 🔐 **Loader natif intégré** (pymloader) avec exécution bytecode native
- 🎯 **Protection bytecode multi-couches** (AES-256 + zlib + marshal)
- 📦 **Module natif auto-compilé** pour exécution sécurisée

# 🛡️ Système de Protection Avancé

Le builder intègre un **système complet de protection du code** avec obfuscation intelligente, chiffrement multi-couches, et anti-analyse.

👉 **[📖 Voir la documentation complète des protections →](build_tools_py/PROTECTION_COMMANDS.md)**

## 🔄 Deux Modes de Protection

### 1️⃣ Mode Interactif (Défaut - Convivial)

Lancez simplement pyMetadidomi sans arguments pour le mode interactif :

```powershell
cd metadidomi-builder/build_tools_py/pyMetadidomi
python pyMetadidomi.py
```

Le programme vous pose des questions interactives pour chaque protection :
- ✅ Anti-débogage
- ✅ Code parasite (junk)
- ✅ Détection VM & Sandbox
- ✅ Anti-reverse engineering
- ✅ Code mort
- ✅ Protection temporelle
- ✅ Chiffrement multi-couches
- ✅ Anti-décompilation
- ✅ Obfuscation (renommage)
- ✅ Compilation EXE (optionnel avec Nuitka)

**Parfait pour** : Tester les protections individuellement, apprendre, ou usage manuel

---

### 2️⃣ Mode CLI (Batch - Automatisé)

Passez des arguments pour automatiser la protection sans interaction :

#### Options Individuelles de Protection

```powershell
# Obfuscation du code (renommage sécurisé)
python pyMetadidomi.py script.py --carbon

# Génération de code parasite inoffensif
python pyMetadidomi.py script.py --junk

# Injection de code anti-débogage
python pyMetadidomi.py script.py --bugs

# Génération de code mort inutile
python pyMetadidomi.py script.py --dead-code

# Protection temporelle (expire après N ans)
python pyMetadidomi.py script.py --time-prot --expiration 2026

# Anti-VM / Anti-virtualisation
python pyMetadidomi.py script.py --anti-vm

# Anti-analyse dynamique
python pyMetadidomi.py script.py --anti-reverse

# Anti-décompilation
python pyMetadidomi.py script.py --anti-decompile

# Chiffrement multi-couches
python pyMetadidomi.py script.py --multi-encrypt

# Chiffrement simple (couche supplémentaire)
python pyMetadidomi.py script.py --encrypt
```

#### Presets de Protection

**Légère (Recommandée pour App Standard)** - Équilibre sécurité/performance
```powershell
python pyMetadidomi.py script.py --light-protection
# Inclut: obfuscation + code junk
# Poids: ~5% augmentation
# Performance: Aucun impact
```

**Moyenne (Recommandée pour App Sensible)** - Protection robuste
```powershell
python pyMetadidomi.py script.py --medium-protection
# Inclut: obfuscation + junk + bugs + dead-code
# Poids: ~15% augmentation
# Performance: Impact négligeable
```

**Lourde (Recommandée pour App Critique)** - Protection maximale
```powershell
python pyMetadidomi.py script.py --heavy-protection
# Inclut: obfuscation + junk + bugs + dead-code + temps + anti-VM + anti-reverse + anti-decompile + multi-encrypt
# Poids: ~40% augmentation
# Performance: Impact modéré (~10% plus lent au démarrage)
```

#### Combinaison Personnalisée

Vous pouvez combiner plusieurs protections individuelles :

```powershell
# Obfuscation + Protection Temporelle + Anti-VM
python pyMetadidomi.py script.py --carbon --time-prot --anti-vm --expiration 2027

# Chiffrement multi-couches + Anti-Décompilation
python pyMetadidomi.py script.py --multi-encrypt --anti-decompile

# Personnalisé complet
python pyMetadidomi.py script.py --carbon --junk --bugs --dead-code --anti-vm --anti-reverse -o script-protected.py
```

#### Options Avancées

```powershell
# Complexité du code mort (1-10, défaut: 10)
python pyMetadidomi.py script.py --dead-code --dead-code-complexity 5

# Fichier de sortie personnalisé
python pyMetadidomi.py script.py --carbon -o mon-app-obfusque.py

# Compiler en EXE après protection (nécessite Nuitka)
python pyMetadidomi.py script.py --medium-protection --compile

# Tout combiné
python pyMetadidomi.py script.py --heavy-protection --output script-final.py --compile
```

---

### Comparaison des Deux Modes

| Aspect | Mode Interactif | Mode CLI |
|--------|-----------------|----------|
| **Utilisation** | `python pyMetadidomi.py` | `python pyMetadidomi.py script.py --carbon` |
| **Interaction** | Questions posées | Aucune (batch) |
| **Idéal pour** | Développement, apprentissage | Automatisation, CI/CD |
| **Vitesse** | Lent (questions) | Rapide (direct) |
| **Configuration** | Visuelle/intuitive | Arguments CLI |
| **Intégration** | Scripts manuels | Pipelines de build |
| **Compilation EXE** | ✅ Proposé à la fin | ✅ Via `--compile` |

**Exemple de CI/CD Pipeline :**
```powershell
# Boucle automatisée sur tous les fichiers
Get-ChildItem -Path "src" -Filter "*.py" | ForEach-Object {
    python pyMetadidomi.py $_.FullName --heavy-protection --output "protected/$($_.BaseName)-protected.py"
}
```

---

## 🛡️ Intégration avec builder.js (Electron)

Le builder Electron propose aussi des options de protection pour les applications Electron :

```powershell
# Protections pour Electron
node build_tools/builder.js --light-protection
node build_tools/builder.js --medium-protection
node build_tools/builder.js --heavy-protection
```

Voir la section **"Commandes principales et options"** ci-dessous pour les détails complets.

---

# Commandes principales et options

Voici toutes les commandes disponibles pour utiliser le builder, avec explications et détails :

## Construction standard (installateur NSIS par défaut)
Lance la construction avec création d'un installateur NSIS professionnel.
```powershell
node build_tools/builder.js
```
- **Résultat** : Un installateur `MonApp-Setup-X.X.X.exe` est créé dans le dossier `dist/`
- **Remarque** : C'est le comportement par défaut du builder

## Construction avec exécutable portable
Crée un exécutable portable au lieu d'un installateur.
```powershell
$env:CREATE_PORTABLE_EXE="true"
node build_tools/builder.js
```
- **Description** : Génère un exécutable portable (`MonApp.exe`) au lieu de l'installateur
- **Option** : L'icône de l'app (`app_src/assets/icon.ico`) sera utilisée si présente.

## Construction sans installateur
Crée uniquement l'exécutable de base sans installateur.
```powershell
$env:SKIP_INSTALLER="true"
node build_tools/builder.js
```
- **Description** : Ignore la création de l'installateur NSIS
- **Résultat** : Seules les ressources de base sont créées (win-unpacked)

## Construction LITE (optimisation des dépendances)
Analyse et exclut les modules inutiles pour un build plus léger. Un rapport est généré.
```powershell
$env:LITE_BUILD="true"
node build_tools/builder.js
```
- **Description** : Seuls les modules réellement utilisés sont conservés dans le package.json.
- **Rapport** : Voir `electron-lite-deps-report.txt` pour le détail des modules inclus/exclus.
- **Résultat** : Un installateur optimisé `MonApp-Setup-X.X.X.exe` est créé

## Construction LITE + Exécutable portable
Combine l'optimisation LITE et la création d'un exécutable portable.
```powershell
$env:LITE_BUILD="true"
$env:CREATE_PORTABLE_EXE="true"
node build_tools/builder.js
```

## Définir la clé de chiffrement
Optionnel ! La clé est générée automatiquement si non fournie.
```powershell
$env:KEY="votre-clé-secrète"
```
- **Description** : Clé personnalisée pour chiffrer le fichier `resources.bin`
- **Si non défini** : Une clé aléatoire de 32 caractères hexadécimaux sera générée automatiquement
- **Vérification** : Le HMAC validera l'intégrité au lancement de l'application

## Signature de code (optionnelle)
Permet de signer l'exécutable final si vous avez un certificat.
```powershell
$env:PFX_PATH="chemin/vers/certificat.pfx"  # Optionnel si dans certs/signing.pfx
$env:PFX_PASS="mot-de-passe-certificat"
```
- **Description** : La signature est automatique si ces variables sont définies. Le chemin de signtool.exe est détecté automatiquement.

## Compression UPX avancée (optionnelle)
La compression UPX est désormais optionnelle. Pour l'activer, définissez la variable d'environnement USE_UPX :
- **Désactivée (par défaut)** :
  ```powershell
  node build_tools/builder.js
  ```
- **Compression rapide (best/force, recommandée)** :
  ```powershell
  $env:USE_UPX="true"
  node build_tools/builder.js
  ```
  > Par défaut, le builder utilise UPX avec l'option `--best --force` (compression rapide et compatible CFG).
- **Ultra-brute (très lent, pour gain maximal)** :
  ```powershell
  $env:USE_UPX="true"
  $env:UPX_ULTRA_BRUTE="true"
  node build_tools/builder.js
  ```
  > Avec `UPX_ULTRA_BRUTE=true`, le builder tente aussi la compression `--ultra-brute --force` (très longue mais parfois plus efficace).
- **Description** : UPX compresse l'exécutable Electron uniquement si USE_UPX est à "true". Le mode ultra-brute est très lent mais peut réduire la taille davantage.

## Structure requise de l'application (`--app-src`)

Quand vous utilisez un projet personnalisé avec `--app-src`, assurez-vous que votre application Electron a cette structure minimale :

```
mon-app/
  package.json        # nom, productName, version, main (obligatoire)
  main.js             # Processus principal Electron (obligatoire)
  preload.js          # Script preload (optionnel)
  index.html          # Interface principale (si référencée dans main.js)
  assets/
    icon.ico          # Icône Windows (optionnelle)
  src/
    renderer.js       # Autres fichiers de l'app
```

**⚠️ IMPORTANT - Fichiers d'exclusion automatique :**

Les fichiers suivants NE SERONT JAMAIS inclus dans l'application construite, même s'ils existent dans votre dossier source :
- `node_modules/` - Dépendances (trop volumineuses)
- `dist/`, `build/` - Builds antérieures
- `.git/`, `.gitignore` - Historique git
- `.env`, `.env.local` - Variables d'environnement sensibles
- `*.pem`, `*.key` - Certificats et clés privées
- `config.build.yaml` - Fichier de configuration du builder (NE PAS inclure ici)
- `package-lock.json` - Lock file du builder

**Exemple :**
```powershell
node build_tools/builder.js --app-src D:\mon-app --output D:\dist
```

Cette commande empaquera uniquement les fichiers nécessaires de votre application, excluant automatiquement les fichiers système, configurations et données sensibles.

## 🏗️ Architecture du Builder - Séparation des Responsabilités

Le builder est conçu selon le principe **"zero pollution"** : 
- ✅ **Le répertoire du builder reste propre** - Aucun fichier généré dedans
- ✅ **Les fichiers générés vont dans le répertoire courant** - Ou là où vous les demandez
- ✅ **Isolation complète** - Le builder n'utilise que ses outils internes (`build_tools/`)

### Répertoires et leur rôle

| Répertoire | Contenu | Rôle | Modifié ? |
|-----------|---------|------|----------|
| `metadidomi-builder/` | Builder + outils | Outil de construction | ❌ Jamais |
| `metadidomi-builder/build_tools/` | Scripts et vendor | Moteur du builder | ❌ Jamais |
| **`.` (répertoire courant)** | Votre application | Source et sortie | ✅ Lecture, génération de démo |
| **`./package.json`** | Config app | ⭐ Généré si manquant | ✅ Créé si absent |
| **`./main.js`, `./preload.js`, `./index.html`** | Code app | ⭐ Générés si manquants | ✅ Créés si absents |
| **`./assets/`** | Ressources | Icônes, images | ✅ Créé si absent |
| **`./dist/`** | Fichiers générés | Installers, exécutables | ✅ Créés ici |
| **`./.build-temp/`** | Fichiers temporaires | Travail intermédiaire | ✅ Auto-nettoyés |

### Flux de compilation
```
┌─────────────────────────────────────┐
│  Node.js + Builder CLI              │
│  node builder.js                    │
└──────────────┬──────────────────────┘
               │
        ┌──────▼──────────────┐
        │  Répertoire courant │
        │  (votre app)        │
        │                     │
        │ • package.json ✅   │
        │ • main.js      ✅   │
        │ • preload.js   ✅   │
        │ • index.html   ✅   │
        │ • assets/      ✅   │
        │                     │
        │ ⭐ Générés si       │
        │    manquants       │
        └──────┬──────────────┘
               │
        ┌──────▼──────┐
        │   Builder   │
        │   Compile   │
        │   Chiffre   │
        │   Empaque   │
        │   Signe     │
        └──────┬──────┘
               │
        ┌──────▼──────────┐
        │ ./dist/         │
        │                 │
        │ ✅ MonApp.exe   │
        │ ✅ Setup.exe    │
        └─────────────────┘
```

## Configuration de la destination de sortie et du dossier source

Par défaut, le builder lit depuis le **répertoire courant** et génère dans **`./dist/`** de ce même répertoire. Vous pouvez personnaliser ces chemins avec les options `--app-src` et `--output` :

### Comportement selon le mode d'utilisation

**Mode par défaut (recommandé)** - Utilise le répertoire courant
```powershell
cd C:\MonApp
node C:\chemin-vers\metadidomi-builder\build_tools\builder.js
# Utilise . (répertoire courant) et génère dans ./dist/
# Si aucun fichier d'app: génère package.json, main.js, etc. à la racine
```

**Mode personnalisé (avec `--app-src`)** - Packager un projet externe
```powershell
node build_tools/builder.js --app-src "C:\Mon\Projet\Electron" --output "C:\dist"
```

**Mode mixte** - Sortie personnalisée, source = répertoire courant
```powershell
cd C:\MonApp
node C:\chemin-vers\metadidomi-builder\build_tools\builder.js --output C:\dist-custom
```

### ✅ Avantages du mode par défaut

- **Intuitif** : Exécutez le builder depuis votre dossier d'application
- **Auto-génération** : Les fichiers de démo sont créés là où vous en avez besoin
- **Aucune modification** des fichiers existants
- **Sortie prévisible** : Toujours dans `./dist/` du répertoire courant
- **Intégration CI/CD** : Facile à scripter et automatiser

### ⚠️ Fichiers automatiquement exclus de l'archive

Quand vous utilisez `--app-src`, les fichiers suivants ne sont **jamais** inclus :
- `node_modules/` - Les modules Node.js (à installer dans votre app)
- `.git/`, `.gitignore`, `.gitattributes` - Fichiers de version control
- `dist/`, `build/`, `out/` - Anciens artefacts de build
- `.next/` - Cache Next.js
- `coverage/` - Fichiers de tests
- `npm-debug.log` - Fichiers de debug
- `package-lock.json` - Lock file

### ✅ Fichiers requis dans votre projet source
```
MonApp/
  ├── package.json          ⭐ Généré si manquant
  ├── main.js               ⭐ Généré si manquant
  ├── preload.js            ⭐ Généré si manquant
  ├── index.html            ⭐ Généré si manquant
  ├── assets/
  │   └── icon.ico          (optionnel)
  └── ...vos autres fichiers
```

### 📝 Exemples pratiques

```powershell
# Mode simple : exécuter depuis le dossier de l'app
cd D:\MonApp
node C:\metadidomi-builder\build_tools\builder.js

# Packager un projet externe
node build_tools/builder.js --app-src D:\mon-app --output D:\dist

# Raccourci --out au lieu de --output
node build_tools/builder.js --out C:\MyApp-dist

# Utilisation avec variables d'environnement
$env:KEY="ma-clé-secrète"
node build_tools/builder.js --app-src C:\MonApp --output C:\MonApp\dist

# Via npm (les arguments sont passés au script build)
npm run build -- --app-src D:\projet --output D:\dist

# Depuis n'importe quel répertoire
cd D:\autre-dossier
node "C:\metadidomi-builder\build_tools\builder.js" --app-src . --output .\dist

# Avec chiffrement et clé personnalisée
$env:KEY="clé-secrète-32-caractères-hex"
node build_tools/builder.js --app-src C:\MonApp --output C:\MonApp\dist
```

### ❌ Erreurs courantes et solutions

**❌ ERREUR** : `Le builder doit être exécuté depuis le répertoire de votre application`
```
[builder] ERREUR: Le builder doit être exécuté depuis le répertoire de votre application.
[builder] Utilisez: node <chemin-builder>/build_tools/builder.js
```
**✅ SOLUTION** : Assurez-vous d'être dans le bon répertoire ou utilisez `--app-src`
```powershell
cd C:\MonApp  # Allez au répertoire de votre app
node C:\metadidomi-builder\build_tools\builder.js
```

**❌ ERREUR** : `Dossier source personnalisé introuvable`
```
[builder] ERREUR: Dossier source personnalisé introuvable: D:\non-existant
```
**✅ SOLUTION** : Vérifiez que le chemin existe et est correct
```powershell
node build_tools/builder.js --app-src "C:\chemin\existant"
```

## Nettoyage et relance du build
Si vous avez des erreurs de suppression de fichiers (EPERM), tuez les processus Electron avant de relancer :
```powershell
taskkill /F /IM electron.exe
node build_tools/builder.js
```

## Résumé des fichiers générés
- `dist/MonApp.exe` : Application portable
- `dist/MonApp-lite.exe` : Version optimisée (LITE)
- `dist/MonApp-Setup.exe` : Installateur NSIS professionnel
- `electron-lite-deps-report.txt` : Rapport d'optimisation LITE

## Personnalisation de l'installateur
L'installateur NSIS utilise automatiquement les informations de votre application :
- Nom et description de l'application
- Version et copyright
- Icône personnalisée (si présente dans `app_src/assets/icon.ico`)
- URLs (site web, support, documentation) depuis package.json
- Informations de licence (si `license.txt` est présent)

Les utilisateurs peuvent personnaliser leur installation avec :
- Choix de l'emplacement d'installation
- Option de raccourci dans le menu Démarrer
- Option de démarrage automatique avec Windows
- Lancement automatique après installation

---

Pour toute option, vous pouvez combiner les variables d'environnement selon vos besoins. Toutes les commandes sont utilisables en PowerShell ou en ligne de commande Windows.

## 🔒 Protection du code source - Approche Pro

Le builder utilise une approche **hybride et non-destructive** pour protéger votre code source :

### ✅ Vos fichiers sources restent INTACTS
**Point crucial** : Le builder ne modifie **jamais** vos fichiers originaux
- ✅ Vos fichiers source restent éditables
- ✅ Vous pouvez continuer à travailler et modifier vos sources
- ✅ Chaque build utilise une copie temporaire
- ✅ Les originaux dans votre répertoire source ne changent jamais

### 🔄 Traitement RÉCURSIF de toute l'application
**Important** : Le builder traite **TOUS les fichiers** à **TOUS les niveaux**
- ✅ Fichiers à la racine
- ✅ Fichiers dans les sous-dossiers (niveau 1, 2, 3, ...)
- ✅ Structure complète préservée
- ✅ **Aucune limite de profondeur**

Cela signifie que même les applications complexes avec plusieurs niveaux de dossiers sont **complètement protégées**.

```
Avant le build:
├── main.js (original, éditables)
├── preload.js (original, éditables)
└── index.html (original, éditable)

Pendant le build:
├── main.js (original, INCHANGÉ ✅)
├── preload.js (original, INCHANGÉ ✅)
├── index.html (original, INCHANGÉ ✅)
└── .build-temp/
    └── temp_protected/
        ├── main.js (copie protégée)
        ├── main.js.jsc (bytecode)
        └── index.html (minimifié)

Après le build:
├── main.js (original, prêt pour modification ✅)
├── preload.js (original, prêt pour modification ✅)
├── index.html (original, prêt pour modification ✅)
└── dist/
    └── [installateur avec fichiers protégés]
```

### 🛡️ Architecture de protection

1. **Copie isolée** : Tous les fichiers sont copiés dans `.build-temp/temp_protected/`
2. **Transformation** : Seules les copies sont obfusquées et compilées
3. **Encapsulation** : Les fichiers protégés sont packagés dans l'application
4. **Source intacte** : Vos originaux restent dans le répertoire source

### 🔐 Compilation en bytecode
- Les fichiers JavaScript sensibles sont compilés en bytecode V8 via bytenode
- Protection native contre la décompilation directe
- Optimisation des performances d'exécution
- Conversion automatique des fichiers .js en .jsc (uniquement dans le build)

### 🚀 Système de fallback intelligent
- Loader généré automatiquement pour chaque fichier
- Compatibilité garantie même si le bytecode échoue
- Contexte d'exécution sécurisé avec isolation
- Gestion transparente des dépendances Node.js

### 🎭 Obfuscation légère
- Protection additionnelle du code de fallback
- Options d'obfuscation sûres et compatibles
- Pas de transformation agressive du code
- Préserve la stabilité de l'application

### 📊 Protection multiniveau
Cette approche assure :
- ✅ **Sécurité maximale** : Protection forte contre l'analyse statique
- ✅ **Flexibilité** : Vous conservez toujours vos sources
- ✅ **Compatibilité** : Fonctionne sur tous les environnements
- ✅ **Performance** : Bytecode offre optimisation d'exécution
- ✅ **Maintenance** : Facile à mettre à jour et modifier

---

## � Protection Automatique du preload.js - Injection de Sécurité

Le builder inclutt une **protection automatique du preload.js** pour sécuriser l'exposition des APIs. Si votre preload.js n'a pas la protection contextBridge nécessaire, le builder l'injecte automatiquement avant l'empaquetage.

### ✅ Vérification et Injection Automatique

Le builder vérifie à chaque build si votre `preload.js` contient :
1. **contextBridge.exposeInMainWorld** - Exposition sécurisée des APIs
2. **allowedModules** - Liste blanche des modules autorisés
3. **Validation des canaux IPC** - Contrôle d'accès aux communications

### 🔍 Comment ça fonctionne

**AVANT le build :**
```
Vérification du preload.js utilisateur
    ↓
Détecte-t-on contextBridge.exposeInMainWorld ?
    ├─ OUI + allowedModules présent → ✅ Accepté (déjà sécurisé)
    ├─ NON ou allowedModules manquant → Injection nécessaire
    │   ↓
    │   Injection automatique du code de sécurité
    │   ↓
    │   ✅ preload.js sécurisé
    │
    └─ Fichier absent → Création d'un preload.js par défaut sécurisé

Build continue avec preload.js sécurisé → EXE final protégé
```

### 📝 Exemple : preload.js AVANT injection

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// ⚠️ INCOMPLET: Pas de protection
contextBridge.exposeInMainWorld('electron', {
    minimize: () => ipcRenderer.send('minimize-window'),
    maximize: () => ipcRenderer.send('maximize-window'),
    close: () => ipcRenderer.send('close-window')
});
```

**Lors du build, le builder ajoute :**
```javascript
// ... votre code existant ...

// 🔐 SÉCURITÉ AUTO-INJECTÉE: Protection contextBridge
// Cette section a été automatiquement ajoutée par le builder pour sécuriser l'accès aux modules Node.js

// Valider que les modules exposés utilisent une liste blanche
const validateAllowedModules = (name, module) => {
  const ALLOWED_MODULES = {
    'electron': ['ipcRenderer', 'ipcMain', 'app'],
    'path': ['join', 'resolve', 'dirname'],
    'fs': ['readFile', 'writeFile'], // Limiter les accès fs
  };
  
  if (!ALLOWED_MODULES[name]) {
    console.warn(`[SECURITY] Module "${name}" non autorisé dans la liste blanche`);
    return false;
  }
  return true;
};
```

### ✅ preload.js Complet et Sécurisé

Si votre preload.js contient déjà la protection complète, le builder le détecte et **ne double-injecte pas** :

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// 🔐 PROTECTION CONTEXTBRIDGE - Exposer les APIs de manière sécurisée
contextBridge.exposeInMainWorld('api', {
    require: (module) => {
        // 📋 Liste blanche des modules autorisés
        const allowedModules = ['electron', 'path'];
        
        if (allowedModules.includes(module)) {
            return require(module);
        }
        throw new Error(`Module "${module}" non autorisé`);
    }
});
```

**Résultat du build :** ✅ Accepté tel quel, pas de modification

### 📊 Cas Gérés par le Builder

| Cas | Détection | Action | Résultat |
|-----|-----------|--------|----------|
| preload.js absent | ❌ Fichier manquant | ✅ Création auto | preload.js sécurisé créé |
| preload.js incomplet | ⚠️ `contextBridge` présent, `allowedModules` absent | ✅ Injection | Protection ajoutée |
| preload.js complet | ✅ Les deux présents | ✅ Validation | Accepté sans modification |
| preload.js dangereux | ⚠️ Aucune protection détectée | ✅ Injection | Protection complète ajoutée |

### 🛡️ Protection Injectée

Le code injecté fournit :
- **Liste blanche des modules** - Seulement les modules autorisés
- **Validation des canaux IPC** - Seulement les canaux sécurisés
- **Gestion des erreurs** - Messages de sécurité clairs
- **Logging** - Traçabilité des accès refusés

### 🔄 Flux Complet de Build avec Sécurité preload.js

```
┌─────────────────────────────────────┐
│ 1. Vérification du preload.js        │
│    ✅ Détecte contexBridge           │
│    ✅ Valide allowedModules          │
│    ✅ Injection si nécessaire        │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 2. Protection Récursive             │
│    ✅ Tous les fichiers protégés     │
│    ✅ À tous les niveaux            │
│    ✅ preload.js inclus             │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 3. Empaquetage ASAR Récursif        │
│    ✅ preload.js sécurisé inclus    │
│    ✅ Structure préservée           │
│    ✅ Tous les fichiers présents    │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 4. Chiffrement et EXE Final         │
│    ✅ Ressources chiffrées          │
│    ✅ preload.js sécurisé           │
│    ✅ Application protégée          │
└─────────────────────────────────────┘
```

### 💡 Avantages de l'Injection Automatique

- ✅ **Sécurité garantie** : Même si vous oubliez la protection
- ✅ **Pas de modification des sources** : Injection dans la copie temporaire
- ✅ **Flexible** : Respecte votre code existant s'il est complet
- ✅ **Transparent** : Vous ne devez rien faire, c'est automatique
- ✅ **Production-ready** : L'EXE final est sécurisé

### ⚙️ Configuration Optionnelle

Si vous voulez utiliser un preload.js personnalisé sans injection :

```powershell
# Assurez-vous que votre preload.js contient :
# 1. contextBridge.exposeInMainWorld(...)
# 2. Une liste blanche de modules autorisés (allowedModules)
# 3. Une validation des accès

# Puis lancez le builder normalement
node build_tools/builder.js
# → Le builder détecte votre protection et ne fait rien
```

### 📝 Exemple Complet : preload.js Sécurisé

Voici un exemple de preload.js qui sera accepté sans injection :

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// 🔐 LISTE BLANCHE: Modules autorisés
const ALLOWED_MODULES = {
  'electron': ['ipcRenderer', 'ipcMain'],
  'path': ['join', 'resolve'],
};

// 🔐 Valider les accès
const validateModule = (module) => {
  if (!ALLOWED_MODULES[module]) {
    throw new Error(`Module "${module}" non autorisé`);
  }
  return true;
};

// 🔐 Exposer l'API sécurisée
contextBridge.exposeInMainWorld('api', {
  invoke: (channel, data) => {
    const ALLOWED_CHANNELS = ['get-data', 'save-file', 'open-dialog'];
    if (ALLOWED_CHANNELS.includes(channel)) {
      return ipcRenderer.invoke(channel, data);
    }
    throw new Error(`Canal "${channel}" non autorisé`);
  },
  
  require: (module) => {
    validateModule(module);
    return require(module);
  }
});
```

**Résultat du build :** ✅ Accepté directement, aucune modification

---

## �📦 Empaquetage ASAR Récursif - Gestion Complète de la Hiérarchie

Le builder utilise une approche **100% récursive** pour l'empaquetage ASAR, garantissant que **TOUS les fichiers** de votre application, peu importe leur profondeur dans la hiérarchie des dossiers, sont **correctement empaquetés** dans l'archive finale.

### ✅ Traitement Complet de la Structure
L'empaquetage ASAR est **entièrement récursif**, ce qui signifie:
- ✅ Fichiers à la racine : **EMPAQUETÉS**
- ✅ Fichiers dans les sous-dossiers (niveau 1) : **EMPAQUETÉS**
- ✅ Fichiers imbriqués profondément (niveau 2, 3, 4, ...) : **EMPAQUETÉS**
- ✅ Structure complète préservée dans l'archive
- ✅ **Aucune limite de profondeur** - Fonctionne avec n'importe quelle complexité

### 🗂️ Exemple de Structure Complexe
```
Application Source:
├── main.js                              (niveau 0 - racine)
├── preload.js                           (niveau 0 - racine)
├── index.html                           (niveau 0 - racine)
│
├── src/                                 (niveau 1)
│   ├── app.js                          ✅ EMPAQUETÉS
│   ├── config.js
│   │
│   ├── components/                      (niveau 2)
│   │   ├── Button.js                   ✅ EMPAQUETÉS
│   │   ├── Modal.js
│   │   │
│   │   ├── ui/                         (niveau 3)
│   │   │   └── Dialog.js               ✅ EMPAQUETÉS
│   │   │
│   │   └── common/                     (niveau 3)
│   │       ├── Header.js
│   │       ├── Footer.js
│   │       │
│   │       └── layouts/                (niveau 4)
│   │           ├── Main.js             ✅ EMPAQUETÉS (même niveau profond !)
│   │           └── Admin.js
│   │
│   └── utils/                           (niveau 2)
│       ├── helpers.js                  ✅ EMPAQUETÉS
│       ├── validators.js
│       │
│       ├── formatters/                 (niveau 3)
│       │   ├── date.js
│       │   └── number.js               ✅ EMPAQUETÉS
│       │
│       └── common/                     (niveau 3)
│           └── constants.js            ✅ EMPAQUETÉS
│
├── lib/                                 (niveau 1)
│   ├── core.js                         ✅ EMPAQUETÉS
│   ├── engine.js
│   │
│   ├── handlers/                        (niveau 2)
│   │   ├── event.js                    ✅ EMPAQUETÉS
│   │   ├── error.js
│   │   │
│   │   └── middleware/                 (niveau 3)
│   │       ├── auth.js
│   │       ├── cors.js
│   │       │
│   │       └── security/               (niveau 4)
│   │           ├── csrf.js             ✅ EMPAQUETÉS (profond !)
│   │           └── sanitize.js
│   │
│   └── helpers/                         (niveau 2)
│       └── utils.js                    ✅ EMPAQUETÉS
│
└── assets/                              (niveau 1)
    ├── images/                          (niveau 2)
    │   ├── logo.png                    ✅ EMPAQUETÉS
    │   └── icons/                      (niveau 3)
    │       └── app.ico                 ✅ EMPAQUETÉS
    │
    ├── styles/                          (niveau 2)
    │   ├── main.css                    ✅ EMPAQUETÉS
    │   └── themes/                     (niveau 3)
    │       └── dark.css                ✅ EMPAQUETÉS
    │
    └── data/                            (niveau 2)
        └── config/                     (niveau 3)
            └── defaults.json           ✅ EMPAQUETÉS
```

### 🔄 Processus d'Empaquetage Récursif

```
1️⃣ Collecte Récursive
   ├─ Traverse tous les répertoires à TOUS les niveaux
   ├─ Collecte chaque fichier trouvé
   ├─ Respecte les exclusions (node_modules, .git, etc.)
   └─ Résultat: Liste COMPLÈTE de tous les fichiers

2️⃣ Création Archive ASAR
   ├─ Ajoute chaque fichier avec son chemin exact
   ├─ Préserve la hiérarchie complète des dossiers
   ├─ Maintient les permissions et métadonnées
   └─ Résultat: app.asar contient TOUS les fichiers

3️⃣ Chiffrement des Ressources
   ├─ Chiffre app.asar → resources.bin (AES-256-CBC)
   ├─ Stocke métadonnées et clés en sécurité
   └─ Résultat: Ressources protégées dans l'EXE

4️⃣ Création EXE Final
   ├─ Bootstrap déchiffre resources.bin au lancement
   ├─ Valide HMAC et watermark
   ├─ Restaure app.asar en mémoire
   └─ Résultat: Application 100% fonctionnelle
```

### ✅ Garanties d'Intégrité

Le builder garantit que :
1. **Tous les fichiers sont inclus** : 100% des fichiers de votre app, à tous les niveaux
2. **Structure préservée** : Les chemins relatifs et hiérarchie sont intacts
3. **Aucun oubli** : Même les fichiers au niveau 5+ sont traités
4. **Fichiers non protégés exclus** : Les cache, node_modules, .git sont correctement exclus
5. **Taille vérifiée** : app.asar contient exactement la taille attendue

### 🎯 Avantages de l'Approche Récursive

| Aspect | Avantage |
|--------|----------|
| **Complétude** | 100% de l'app est protégée, aucun fichier oublié |
| **Profondeur** | Aucune limite - fonctionne avec n'importe quelle complexité |
| **Flexibilité** | Supporte tous les patterns d'organisation (src, lib, components, etc.) |
| **Performance** | Traversée efficace même pour applications très volumineuses |
| **Fiabilité** | Gestion cohérente de tous les types de fichiers |
| **Maintenance** | Facile d'ajouter du code en profondeur - le builder gère automatiquement |

### 💡 Exemple: Ajout de Nouveaux Fichiers Profonds

Après le premier build, si vous ajoutez de nouveaux fichiers profonds :

```javascript
// Avant: structure existante
src/
  components/
    common/
      Header.js

// Après: vous ajoutez
src/
  components/
    common/
      layouts/
        Main.js  ← Nouveau fichier, niveau 4 !

// Lors du build suivant:
✅ Le builder détecte automatiquement et inclut Main.js
✅ Aucune configuration supplémentaire nécessaire
✅ L'application reste protégée complètement
```

### 🚀 Performance et Optimisation

L'empaquetage récursif est optimisé pour :
- **Vitesse** : Collecte efficace même pour des milliers de fichiers
- **Mémoire** : Traitement par streaming pour gros fichiers
- **Réseau** : 100% offline, aucun téléchargement externe
- **Compatibilité** : Format ASAR standard, compatible Electron

---

## 📊 Comparaison avec electron-builder

### metadidomi-builder vs electron-builder

| Critère | metadidomi-builder | electron-builder |
|---------|-------------------|------------------|
| **Installation** | 100% offline, vendor local | NPM global ou projet |
| **Dépendances** | Minimal (electron) | Nombreuses |
| **Configuration** | Variables env + builder.js | Config JSON/YAML complexe |
| **Personnalisation** | Code source modifiable | Limitée aux options |
| **Chiffrement ressources** | ✅ AES-256 intégré | ❌ Nécessite addon |
| **Bytecode protection** | ✅ bytenode natif | ❌ Pas de support |
| **Obfuscation** | ✅ javascript-obfuscator intégré | ❌ Plugin externe requis |
| **NSIS personnalisé** | ✅ Template NSIS modifiable | ✅ NSIS support |
| **UPX compression** | ✅ Optionnel configurable | ❌ Pas de support |
| **Mode LITE** | ✅ Analyse dépendances | ❌ Pas de support |
| **Watermarking** | ✅ HMAC + métadonnées build | ❌ Pas de support |
| **Signature code** | ✅ Auto-signé + custom | ✅ Support |
| **Build reproducible** | ✅ Oui | ⚠️ Partiel |
| **Courbe apprentissage** | Moyen (modèle Node.js) | Élevé (nombreuses options) |

### Score et recommandations

**metadidomi-builder** : 9/10 ⭐
- ✅ Idéal pour applications Electron nécessitant **sécurité maximale**
- ✅ Parfait pour builds **100% offline** et reproductibles
- ✅ Excellent pour **protection du code source** (bytecode + obfuscation)
- ✅ Meilleur choix pour **chiffrement de ressources**
- ✅ Great for **customization** et intégration CI/CD complexe
- ⚠️ Requiert Node.js et compréhension du processus de build

**electron-builder** : 7/10 ⭐
- ✅ Solution standard et éprouvée
- ✅ Documentation extensive et communauté large
- ✅ Configuration JSON/YAML simple
- ✅ Support de multiples plateformes (Mac, Linux)
- ❌ Moins de contrôle sur les détails du build
- ❌ Dépendances nombreuses et mises à jour fréquentes

### Recommandations pour développeurs Electron

**Choisir metadidomi-builder si :**
1. Vous avez besoin de **protection forte du code source**
2. Vous travaillez dans un environnement **sans accès Internet**
3. Vous voulez des **builds reproductibles et déterministes**
4. Vous avez besoin de **chiffrement de ressources**
5. Vous préférez **contrôler chaque étape** du build
6. Vous développez une application **sensible** (données, algo propriétaire)

**Choisir electron-builder si :**
1. Vous avez besoin de build multi-plateformes (Windows, Mac, Linux)
2. Vous préférez une **configuration simple et rapide**
3. Vous avez une application **standard** sans besoins spéciaux
4. Vous voulez des **mises à jour automatiques** (Squirrel)
5. Vous privilégiez la **stabilité et la compatibilité**

### Conclusion
**metadidomi-builder** est une solution **premium** pour les équipes ayant des exigences de sécurité élevées et souhaitant maîtriser tous les aspects du processus de construction. Pour les autres projets, electron-builder reste le choix logique et éprouvé.

---

## � Packaging d'Applications Python

Le builder inclut aussi un **système complet de packaging Python** via `builder.py` pour créer des applications Windows standalone avec installateurs NSIS professionnels.

### 🚀 Démarrage Rapide - Applications Python

**Le plus simple** - Exécutez le builder Python depuis votre dossier d'application :

```powershell
# Depuis votre répertoire d'application Python
cd D:\mon-app-python
python D:\chemin-vers\metadidomi-builder\build_tools_py\builder.py

# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Ou avec options personnalisées :**

```powershell
# Source et sortie personnalisées
python builder.py --app-src D:\mon-app --output D:\dist

# Mode GUI (sans fenêtre console)
python builder.py --gui

# Combiné
python builder.py --app-src D:\mon-app --output D:\dist --gui
```

### 📋 Structure Minimale d'une Application Python

Le builder détecte automatiquement une application Python valide avec ces fichiers :

```
mon-app-python/
  ├── config.py              ⭐ Configuration (généré si manquant)
  ├── __main__.py            ⭐ Point d'entrée (généré si manquant)
  ├── assets/                ⭐ Ressources (créé si manquant)
  │   └── icon.ico           (optionnel - utilisé dans l'installateur)
  └── ...vos autres fichiers
```

### ✅ Fichiers Requis vs Optionnels

| Fichier | Requis | Description | Auto-généré |
|---------|--------|-------------|-------------|
| `config.py` | ⭐ | Configuration app (nom, version, auteur) | ✅ Oui |
| `__main__.py` | ⭐ | Point d'entrée principal | ✅ Oui |
| `main.py` | ⚠️ | Alternative à `__main__.py` | ✅ Dépistage auto |
| `app.py` | ⚠️ | Alternative au point d'entrée | ✅ Dépistage auto |
| `assets/` | ❌ | Dossier de ressources | ✅ Créé vide |
| `assets/icon.ico` | ❌ | Icône Windows (.ico) | ❌ Non |

**Priority de détection du point d'entrée :** `__main__.py` → `main.py` → `app.py` → `run.py` → `start.py`

### 📝 Exemple : config.py Minimal

```python
# Configuration de l'application
APP_NAME = "MonApp"
VERSION = "1.0.0"
DESCRIPTION = "Application Python"
AUTHOR = "Votre Entreprise"
ENTRY = "__main__"
```

### 📝 Exemple : __main__.py Minimal

```python
#!/usr/bin/env python3
"""
Point d'entrée principal de l'application Python
"""
import sys

def main():
    print("MonApp v1.0.0")
    print("Application Python construite avec Metadidomi Builder")

if __name__ == "__main__":
    main()
```

### 🎨 Applications Python avec Tkinter (Interface Graphique)

Pour les applications avec interface graphique **Tkinter** :

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Application Tkinter avec interface graphique
"""
import tkinter as tk
from tkinter import ttk, messagebox

class MonApp(tk.Tk):
    def __init__(self):
        super().__init__()
        
        self.title("MonApp")
        self.geometry("500x300")
        self.resizable(False, False)
        
        # UI Elements
        label = ttk.Label(self, text="Bienvenue dans MonApp")
        label.pack(pady=20)
        
        button = ttk.Button(self, text="Cliquez-moi", command=self.on_click)
        button.pack(pady=10)
    
    def on_click(self):
        messagebox.showinfo("Info", "Bouton cliqué!")

if __name__ == "__main__":
    app = MonApp()
    app.mainloop()
```

**Builder avec GUI :**
```powershell
# Mode GUI: pas de fenêtre console
python builder.py --gui

# Par défaut: mode console (fenêtre noire reste visible)
python builder.py
```

### 🔍 Auto-Génération Intelligente

Si vous exécutez le builder dans un dossier vide, il génère automatiquement :

```
Dossier vide
    ↓
python builder.py
    ↓
Génération automatique:
  ✅ config.py
  ✅ __main__.py
  ✅ assets/ (dossier vide)
    ↓
  Fichiers de démo prêts à modifier
```

### 🎛️ Modes de Compilation

#### Mode Console (Défaut)
La fenêtre console reste visible quand l'app s'exécute :
```powershell
python builder.py
# ➜ Fenêtre console visible
# ➜ Idéal pour les apps CLI
```

#### Mode GUI
Compile sans fenêtre console (parfait pour les apps Tkinter) :
```powershell
python builder.py --gui
# ➜ Pas de fenêtre console
# ➜ Idéal pour les apps GUI
```

### 🔧 Paramètres Avancés

| Paramètre | Description | Exemple |
|-----------|-------------|---------|
| `--app-src <chemin>` | Dossier source (défaut: cwd) | `--app-src D:\mon-app` |
| `--output <chemin>` | Dossier sortie (défaut: ./dist) | `--output D:\dist` |
| `--out <chemin>` | Alias pour --output | `--out .\dist` |
| `--gui` | Compiler en mode GUI (pas de console) | `--gui` |
| `--no-pyc` | Ne pas compiler les .py en .pyc | `--no-pyc` |
| `--key <clé>` | Clé de chiffrement personnalisée | `--key ma-clé` |

### 💾 Architecture du Packaging Python

Le builder Python utilise une approche **4 étapes** pour protéger et packager votre application :

```
ÉTAPE 1: Archive ZIP Récursive
   ├─ Collecte TOUS les fichiers de l'app
   ├─ À TOUS les niveaux de profondeur
   └─ Crée une archive ZIP chiffrée

ÉTAPE 2: Chiffrement + HMAC
   ├─ Chiffrement Fernet (AES-128)
   ├─ Calcul HMAC-SHA256 (intégrité)
   └─ Bundle sécurisé créé

ÉTAPE 3: Bootstrap d'Auto-Extraction
   ├─ Code Python d'extraction
   ├─ Déchiffrement automatique
   ├─ Validation d'intégrité
   └─ Exécution du code métier

ÉTAPE 4: Compilateur C Launcher
   ├─ Injection dynamique du code Python
   ├─ Compilation du launcher.exe avec GCC
   ├─ Support console ET GUI
   └─ EXE Windows standalone
```

### 🏗️ Flux Complet de Compilation

```
Source Python
    ↓
[Étape 1: Collecte Récursive]
    ├─ Lit tous les fichiers (.py, config, etc.)
    ├─ Exclusion auto: __pycache__, .git, node_modules
    └─ Crée archive.zip chiffrée
    ↓
[Étape 2: Chiffrement Fernet]
    ├─ Chiffre archive.zip → encrypted.bin
    ├─ Calcule HMAC-SHA256
    └─ Clé générée ou personnalisée
    ↓
[Étape 3: Bootstrap Python]
    ├─ Code d'extraction créé
    ├─ Contient clé + HMAC
    └─ Sera injecté dans launcher C
    ↓
[Étape 4: Launcher C + Injection]
    ├─ Code Python injecté dans launcher.c
    ├─ Compilation GCC (console ou GUI)
    ├─ Génère launcher.exe (50-100 KB)
    └─ Minimal et autonome
    ↓
[Étape 5: Installateur NSIS]
    ├─ Bundle launcher.exe + Python Embeddable
    ├─ Crée installateur .exe professionnel
    └─ Sortie: MonApp-Setup-1.0.0.exe
    ↓
Installateur Final
```

### 🐍 Python Embeddable Automatique

Le builder utilise **Python Embeddable** pour les utilisateurs finaux :

- ✅ Python 3.11.9 autonome (64 bits Windows)
- ✅ Pas d'installation système requise
- ✅ Zéro dépendance extérieure
- ✅ Distribution portable
- ✅ Inclus dans l'installateur NSIS

**Localisation :** `build_tools/vendor/python_embeddable/`

### 🔐 Protection du Code Python

1. **Compilation en Bytecode** (optionnel)
   - Les fichiers `.py` compilés en `.pyc`
   - Protège contre la lecture directe du source

2. **Chiffrement Fernet**
   - Archive ZIP chiffrée en AES-128
   - Extraction en mémoire à l'exécution
   - Clé générée automatiquement ou personnalisée

3. **Validation HMAC**
   - Vérification d'intégrité des archives
   - Détecte les modifications
   - Arrête l'exécution si compromis

4. **Launcher C Minimaliste**
   - Seulement 50-100 KB
   - Code Python injecté dynamiquement
   - Exécution directe sans interpréteur externe

### 📊 Fichiers Générés

```
dist/
  └── MonApp-Setup-1.0.0.exe     ← Installateur NSIS professionnel
     Contient:
      ├─ launcher.exe             (50-100 KB)
      ├─ Python 3.11.9 Embeddable (35-40 MB)
      ├─ Votre code Python        (chiffré)
      └─ Ressources et assets
```

**Taille finale :** 50-150 MB selon la complexité de l'app

### 🚀 Exemples Complets

#### Exemple 1 : Application Console Simple

```powershell
# Structure
mon-app/
  config.py
  __main__.py

# Build
cd mon-app
python ..\builder.py
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

#### Exemple 2 : Application Tkinter GUI

```powershell
# Structure
mon-app-gui/
  config.py
  __main__.py         ← Interface Tkinter
  assets/
    icon.ico

# Build (mode GUI pour éviter console)
cd mon-app-gui
python ..\..\builder.py --gui
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (pas de console)
```

#### Exemple 3 : Application Complexe Multi-Fichiers

```powershell
# Structure complexe
mon-app/
  config.py
  __main__.py
  utils/
    helpers.py
    validators.py
  lib/
    core.py
    handlers/
      events.py
  assets/
    icon.ico
    data.json

# Build avec sortie personnalisée
python builder.py --app-src D:\mon-app --output D:\dist
# TOUS les fichiers récursivement inclus ✅
```

### ⚙️ Options Avancées de Compilation

#### Avec Clé de Chiffrement Personnalisée

```powershell
# Option 1: Via argument
python builder.py --key "ma-clé-secrète-32-caractères"

# Option 2: Via variable d'environnement
$env:KEY = "ma-clé-secrète-32-caractères"
python builder.py
```

#### Sans Compilation .pyc

```powershell
python builder.py --no-pyc
# Les .py restent non compilés (plus rapide au build)
```

#### Combinaisons

```powershell
# GUI + sortie personnalisée + clé custom
python builder.py --gui --output D:\dist --key "clé-secrète"

# Source custom + GUI + sans .pyc
python builder.py --app-src D:\mon-app --gui --no-pyc
```

### 🔍 Dépistage et Débogage

Le builder affiche un **rapport détaillé** du processus :

```
[builder] 🚀 Metadidomi Python Builder
[builder] Architecture: compatible builder.js (Archive ZIP → Fernet → NSIS)
[builder]
[builder] 📂 Configuration:
[builder]   Source:     D:\mon-app
[builder]   Sortie:     D:\mon-app\dist
[builder]   Temporaire: D:\mon-app\.build-temp
[builder]
[builder] 📋 Informations de l'application:
[builder]   Nom:     MonApp
[builder]   Version: 1.0.0
[builder]   Auteur:  Votre Entreprise
[builder]
[builder] 🔑 Clé de chiffrement auto-générée: a1b2c3d4e5f6...
[builder]
[builder] 🛠️  PHASES DE CONSTRUCTION:
[builder]
[builder] 📦 ÉTAPE 1: Empaquetage récursif...
[builder]   📄 config.py (1.2 KB)
[builder]   📄 __main__.py (2.5 KB)
[builder]   📄 utils/helpers.py (3.1 KB)
[builder]
[builder] ✅ Collecte terminée: 15 fichiers
[builder] 📊 Taille totale: 45.2 MB
```

### 📋 Fichiers Automatiquement Exclus

Le builder **exclut toujours** ces fichiers/dossiers :

- `__pycache__/` - Cache Python compilé
- `.git/`, `.gitignore` - Version control
- `node_modules/` - Dépendances Node (si mixed)
- `dist/`, `build/` - Anciens builds
- `.env`, `.env.local` - Variables sensibles
- `*.pyc`, `*.pyo` - Fichiers compilés
- `config.build.yaml` - Config du builder
- `.build-temp/` - Fichiers temporaires

### ✅ Vérification Post-Build

Après la compilation, vérifiez l'installateur :

```powershell
# Vérifier la présence du fichier
ls dist/
  -Mode     LastWriteTime    Length Name
  -----     ---------------  ------ ----
  -a----    14/11/2025 10:30   85 MB MonApp-Setup-1.0.0.exe

# Installer et tester
.\dist\MonApp-Setup-1.0.0.exe
# → Fenêtre NSIS d'installation
# → Installation dans Program Files
# → Lancement de MonApp
```

### 🐛 Dépannage Courant

**❌ ERREUR: UnicodeDecodeError dans la console PowerShell**
```
UnicodeDecodeError: 'charmap' codec can't decode byte...
```
**✅ SOLUTION:** Le builder force UTF-8 automatiquement. Si toujours problématique :
```powershell
$env:PYTHONIOENCODING = "utf-8"
python builder.py
```

**❌ ERREUR: GCC non trouvé (compilation du launcher)**
```
MinGW64 GCC not found
```
**✅ SOLUTION:** Installez MinGW64 ou modifiez le PATH :
```powershell
# Via chocolatey
choco install mingw
# OU manuellement via https://www.mingw-w64.org/
```

**❌ ERREUR: Python Embeddable non trouvé**
```
Python Embeddable distribution not found
```
**✅ SOLUTION:** Vérifiez le dossier `build_tools/vendor/python_embeddable/`

### 📞 Support

Pour les questions sur le packaging Python :
- 📖 Consultez ce README
- 🐛 Vérifiez les logs du builder
- 💬 Contactez ETS METADIDOMI

---

## �🗺️ Roadmap - Vision Multi-Plateforme

### Phase 1 : Windows ✅ (Actuelle)
- ✅ Build portable (.exe)
- ✅ Installateur NSIS professionnel
- ✅ Signature de code Windows
- ✅ Protection bytecode + obfuscation
- ✅ Chiffrement AES-256 des ressources
- ✅ Mode LITE d'optimisation
- ✅ 100% offline

### Phase 2 : macOS (Q1 2026)
- Création de DMG et PKG natifs
- Signature de code macOS (codesign)
- Notarization Apple automatique
- Support ARM64 et Intel
- Protection bytecode identique à Windows

### Phase 3 : Linux (Q2 2026)
- Build AppImage et Snap
- Support Debian/RPM
- Packaging cross-distribution
- Protection bytecode uniforme

### Phase 4 : Fonctionnalités Avancées (Q3 2026+)
- Updates automatiques multi-plateforme
- Delta updates (téléchargement optimisé)
- Telemetry anonyme optionnelle
- Support de plugins natifs

---

## 📞 Support et Contribution

**metadidomi-builder** est développé par **ETS METADIDOMI**.

Pour rapporter des bugs, suggérer des features ou contribuer : consultez les guidelines de contribution.

---
