# 🥗 Initial Data Analysis (IDA) — Does Cooking Help You Stay Healthy?

This is my first R project focused on exploring the relationship between cooking habits and health, based on Body Mass Index (BMI). The data is drawn from a CSV file (`Project1Data.csv`) and analyzed using R with `tidyverse` and `ggplot2`.

## 📊 Objective

**Research Question:**  
*Does cooking your own meals really help to maintain good health?*

Using a dataset of individuals and their health metrics, this analysis investigates whether the frequency of cooking at home correlates with healthier BMI categories.

## 🛠 Tools & Packages Used

- R  
- `tidyverse`  
- `dplyr`  
- `ggplot2`

## 🔍 What the Code Does

1. **Loads the dataset** using `read.csv()` and inspects its structure with `str()`.
2. **Classifies BMI values** into labeled categories such as *Underweight*, *Normal*, *Overweight*, etc.
3. **Reorders the cooking frequency levels** for consistent plotting.
4. **Creates a bar chart** using `ggplot2`:
   - X-axis: Frequency of cooking at home
   - Fill: Health status based on BMI
   - Custom color coding for each BMI category
   - Includes a clear title and legend for easy interpretation

## 📈 Output

The generated plot shows the distribution of individuals across different health statuses (based on BMI) for each level of cooking frequency. It offers a visual insight into whether frequent home cooking is associated with healthier BMI ranges.

---

Feel free to check out the R code and explore the results in the provided html file!
