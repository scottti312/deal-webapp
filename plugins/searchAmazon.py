from bs4 import BeautifulSoup
import requests
import sys
 
def search(URL):
    HEADERS = ({'User-Agent':
                'Mozilla/5.0 (X11; Linux x86_64)'
                    'AppleWebKit/537.36 (KHTML, like Gecko)'
                            'Chrome/44.0.2403.157 Safari/537.36',
                                'Accept-Language': 'en-US, en;q=0.5'})
 
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    title = soup.find("span", attrs={"id": 'productTitle'})
    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)
 
    price_code = soup.findAll("span", attrs={"class": "a-price a-text-price a-size-medium apexPriceToPay"})
    pricea = []
    for p in price_code:
        pricea.append(p.find("span", attrs={"class": "a-offscreen"}))
    price = pricea[0]
    price_string = price.string
    print(price.string)

    img = soup.find("img", attrs={"id": "landingImage"})
    print(img.get("src"))

    return price_string, title_string

url = sys.argv[1]
# url = "https://www.amazon.com/HIFIMAN-Full-Size-Audiophile-Adjustable-Headphone/dp/B07K59HW9R/?_encoding=UTF8&pd_rd_w=dKAIE&pf_rd_p=ed0a22d3-02a9-43b9-a7a8-2632c9eb2ad8&pf_rd_r=BMVWANTXYDRYBY2R497J&pd_rd_r=38ea6469-0b66-49b5-b0f1-0e243a5c4b4e&pd_rd_wg=uyxNX&ref_=pd_gw_ci_mcx_mr_hp_atf_m"
search(url)
