# 🧹 Exploratory Data Analysis & Data Cleaning  
### *Australian Health Survey (AHS) Project*

[![View Final Report](https://img.shields.io/badge/📄%20View%20Report-Click%20Here-brightgreen?style=flat-square)](https://maiminhhh.github.io/maiminhhh/stat3888/)

---

## 🚀 Overview

This project showcases my ability to clean, structure, and explore complex real-world data. The dataset — a subset of the **Australian Health Survey (AHS)** — simulates challenges often faced in industry: messy inputs, inconsistent formats, metadata embedded in external Excel sheets, and custom logic for missing values.

### ✅ Objectives:
- Clean and validate biomedical, nutrient, and food diary data
- Integrate disparate sources using subject and household identifiers
- Diagnose missingness and document handling strategies
- Deliver tidy, analysis-ready datasets for further modeling

---

## 📦 Dataset Summary

| File                         | Description                              |
|------------------------------|------------------------------------------|
| `AHS11biomedical.csv`        | Biomedical indicators per individual     |
| `AHS11nutrient.csv`          | Nutrient intake estimates                |
| `AHS11food.csv`              | Two-day food diary records               |
| `nutmstatDataItems2019.xlsx` | External data dictionary + variable types|

**Identifiers:**
- `ABSPID`: Unique person ID (primary key across datasets)
- `ABSHID`: Household ID (for grouping by household)

---

## 🛠 Cleaning Strategy

Built using a **modular R pipeline**, the cleaning workflow includes:
- Variable typing and labeling with external metadata
- Standardized treatment of **special missing value codes**
- Merge and join logic preserving one-to-many food diary entries
- Documentation of every transformation for full reproducibility

The final cleaned data is saved as:
- `clean_data.Rdata` – For direct loading
- `clean_data_summary.html` – For exploratory reporting

---

## 🌐 Final Report

📄 [Click here to view the interactive HTML report](https://maiminhhh.github.io/maiminhhh/stat3888/)

> Includes: missingness diagnostics, summary stats, structure tables, and variable-by-variable insights.

---

## 🧰 Tools Used

- **Language**: R  
- **Packages**: `tidyverse`, `janitor`, `readxl`, `haven`, `skimr`, `here`  
- **Reporting**: Quarto / RMarkdown → HTML  
- **Version Control**: Git + GitHub

---

## 🧠 Skills Demonstrated

- Robust data cleaning and validation  
- Metadata-driven wrangling and typing  
- Diagnosing structured and unstructured missingness  
- Reproducible pipeline development  
- Effective communication of data issues through automated reports

---

> This project was submitted as part of a data science job application and demonstrates practical, industry-relevant EDA and cleaning skills.

