# KIT Language Courses
A scraper for language courses of the KIT Sprachzentrum

## Usage

#### Print a table
```sh
java -jar build/libs/kit-language-courses-*.jar 
```
```
┌───────────────┬──────────────────┬───────┬─────┬────┬───────────────┬──────┬───┬────────────┐
│ Arabisch 1a   │ Dr. Gharieb      │ 50.20 │ 256 │ Di │ 13.45 - 15.15 │ € 90 │ 2 │ Arabisch   │
│ Arabisch 1b   │ Abd Alla         │ 50.20 │ 301 │ Mo │ 19.30 - 21.00 │ € 90 │ 2 │ Arabisch   │
│ Arabisch 2    │ Dr. Gharieb      │ 50.20 │ 256 │ Di │ 15.15 - 16.45 │ € 90 │ 2 │ Arabisch   │
│ Chinesisch 1a │ Lin              │ 50.20 │ 256 │ Di │ 19.30 - 21.00 │ € 90 │ 2 │ Chinesisch │
│ Chinesisch 1b │ Wang             │ 50.20 │ 207 │ Mo │ 19.30 - 21.00 │ € 90 │ 2 │ Chinesisch │
│ Chinesisch 1c │ Wang             │ 50.20 │ 207 │ Mi │ 19.30 - 21.00 │ € 90 │ 2 │ Chinesisch │
│ Chinesisch 1d │ Zhang-Backenstos │ 50.20 │ 301 │ Do │ 19.00 - 20.30 │ € 90 │ 2 │ Chinesisch │
...
```

#### Print JSON
```
java -jar build/libs/kit-language-courses-*.jar -json
```
```json
[
  {
    "name": "Arabisch 1a",
    "lecturer": "Dr. Gharieb",
    "building": "50.20",
    "room": "256",
    "weekday": "Di",
    "time": "13.45 - 15.15",
    "price": "€ 90",
    "ects": "2",
    "language": "Arabisch"
  },
  {
    "name": "Arabisch 1b",
    "lecturer": "Abd Alla",
    "building": "50.20",
    "room": "301",
    "weekday": "Mo",
    "time": "19.30 - 21.00",
    "price": "€ 90",
    "ects": "2",
    "language": "Arabisch"
  },
  ...
  ```
