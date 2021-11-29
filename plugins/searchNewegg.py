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

    title = soup.find("h1", attrs={"class": "product-title"})
    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.findAll("li", attrs={"class": "price-current"})
    pricea = []
    for p in price_code:
        pricea.append(p.find("strong").string)
        pricea.append(p.find("sup").string)
    price = '$'
    price += ''.join(pricea)
    print(price)

url = sys.argv[1]
# url = "https://www.newegg.com/p/1TS-00BK-00056?Item=9SIANGPFYC1672&Description=CPU&cm_re=CPU-_-9SIANGPFYC1672-_-Product&cm_sp=SP-_-743350-_-0-_-6-_-9SIANGPFYC1672-_-CPU-_-cpu-_-13"
search(url)