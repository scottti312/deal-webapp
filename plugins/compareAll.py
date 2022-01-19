import searchAmazon as am
import searchBestBuy as bb
import searchNewegg as ne
import searchOfficeDepot as od
import searchWalmart as wm

query = 'wh-1000xm4'

print("Amazon: ")
am.search(am.find(query))
print("Best Buy: ")
bb.search(bb.find(query))
print("Newegg: ")
ne.search(ne.find(query))
print("Office Depot: ")
od.search(od.find(query))
