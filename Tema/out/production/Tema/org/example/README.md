# Tema: Generator de Subscriptii si Publicatii
**Realizat de: Carcea Diana și Carcea Răzvan**
## Cerința:
Implementarea unui program care să genereze aleator **seturi de subscriptii și publicatii** cu următoarele cerințe:
- 📦 **Publicațiile** au o structură fixă de câmpuri (ex: stationid, city, temp, rain, wind, direction, date)
- 🧩 **Subscrițiile** au un subset aleator de câmpuri, dar:
  - Procentul de apariție al fiecărui câmp este configurabil (ex: `city` apare în 90% din subscripții)
  - Pentru cel puțin un câmp (ex: `city`), se poate seta procentul de apariții cu operatorul `=`
- ⚙️ Programul permite configurarea:
  - Numărului de mesaje generate (subscripții/publicații)
  - Frecvenței câmpurilor în subscripții
  - Procentului de utilizare al operatorului `=` pentru un câmp ales
- 🧵 Include o implementare **paralelizată** cu threads pentru generare mai rapidă

## Ealuarea implementării:

### 🔧 Tipul de paralelizare: Threads  
### ⚙️ Factorul de paralelism testat
| Nr. Threads / Procese | Descriere                    |
|------------------------|------------------------------|
| 1                      | Fără paralelizare (secvențial) |
| 4                      | Paralelizare cu 4 threads     |
| 10                      | Paralelizare cu 10 threads     |

### 📊 Numarul de mesaje generate

- **Publicatii:** `10.000`
- **Subscriptii:** `10.000`

### ⏱️ Timp de executie

| Nr. Threads | Publicatii (ms) | Subscriptii (ms) |
|-------------|------------------|------------------|
| 1           | 721             | 130             |
| 4           | 154             | 87             |
| 10           | 158             | 104             |

### 🖥️ Specificatii sistem de test

- **Procesor:** Intel Core i5-6500 @ 3.20 GHz
- **Număr de nuclee fizice:** 4
- **Număr de threads/logical cores:** 4 threads
- **RAM:** 16GB
- **Sistem de operare:** Windows 11
- **Java Version:** OpenJDK 21
