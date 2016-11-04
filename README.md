# DownLoadScheduler
Computer hard drives are made of spinning platters, which hold billions (or even trillions) of little magnetic cells, each of which holds a 0 or a 1 in its polarity. In these cells, bits are stored, and cells together hold the data and files on most computers. The data in hard drives is read by a "read-write head" that picks a particular circle on the hard drive, and reads all the data as the hard disk spins. Due to hard drive construction, file I/O is fastest when bits are read sequentially (no jumping around between files). The Insta-Cram corporation owns a server farm in a top-secret location on Long Island, from where they deliver illicit videos of professors delivering computer science lectures, which were stealthily recorded and furtively uploaded by enterprising students. Since their service is usually free, there is a lot of demand for downloading their videos. Insta-cram stores their videos on multiple hard-drives, each tied to a different server. They have hired you to write a download scheduler for their service, in order to make sure that users who request videos first are served first. Additionally, Insta-Cram offers a premium membership, where, for the low price of $2.14 per month, a user gets to access the next available download slot, bypassing the free users. You will be implementing the download scheduler as a set of queues. Each download will have a size between approximately 114 and 219 megabytes, selected randomly. You will be given a number of servers to work with; all of these will have a fixed file transfer speed. You will get a number of premium and free users, with a download request probability for each category at every given second (timestep). The servers will have to complete one job before starting the next, and every time the premium users request a job, it must be given the next available time slot. At the end of the "day" (at the end of program execution), you will be required to print some stats, including the total number of jobs served to premium customers, total number of jobs served to regular customers, the total number of megabytes served to premium customers, and the total number of megabytes served to regular customers (the number of megabytes should get updated after file download is completed).