# 🏪 Evaluation of a Potential Store Location for Dear Klairs in Sydney

📍 *University of Sydney – Foundations of Data Science*  
📅 *May 2023*  
🎯 *Client: [Dear Klairs](https://www.klairscosmetics.com)*  
👥 *Individual contribution from a student group project*

---

## 🧴 About the Client

**Dear Klairs** is a Korean skincare brand established in 2010.  
- Operates in **1,000 distribution channels** across **40 countries**  
- Strives to **grow into a global skincare brand** with direct customer engagement  
- Seeks insights for selecting a **strategic store location** in **Sydney, Australia**

---

## 📌 Objective

To evaluate potential locations for an **official Dear Klairs store** in Sydney using public transport usage data, with a particular focus on **light rail station traffic** as a proxy for foot traffic and urban accessibility.

---

## ✅ Key Recommendations

- Light rail station proximity is a reasonable and accessible choice for the store.
- Stations located at the **center** of the Sydney light rail network offer the **highest potential** due to high traffic volume.
- **Eastern and Western** network areas are also suitable, but with relatively lower traffic.

---

## 📊 Data Source

Data was sourced from the official **NSW Government Open Data Portal**:

🔗 [Opal Trips – Light Rail](https://opendata.transport.nsw.gov.au/dataset/opal-trips-light-rail)

- Dataset includes:
  - **47 light rail stations** in Sydney
  - Monthly usage statistics for **January 2023**
  - Total trips per station

Stations with unknown or inactive status were excluded from the analysis.

---

## 🔍 Analysis Approach

The project involves:

- **Filtering relevant and active stations**
- **Aggregating total trips** by station
- **Comparing usage between warm and cold months**
- **Performing a 2-sample t-test** to determine if temperature impacts ridership

📈 **Tools used**:  
`R`, `dplyr`, `basictabler`, statistical inference via `t.test()`

---