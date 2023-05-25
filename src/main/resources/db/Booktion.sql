
CREATE TABLE clients
(
	clientId               VARCHAR2(50)  NOT NULL ,
	password             VARCHAR2(50)  NOT NULL ,
	name                 VARCHAR2(50)  NOT NULL ,
	email                VARCHAR2(100)  NOT NULL ,
	phoneNumber          VARCHAR2(50)  NOT NULL ,
	zipCode              VARCHAR2(20)  NOT NULL ,
	address1             VARCHAR2(100)  NOT NULL ,
	address2             VARCHAR2(100)  NULL ,
	CONSTRAINT  XPKclient PRIMARY KEY (clientId)
);

CREATE TABLE book
(
	bookId               NUMBER  NOT NULL ,
	isbn                 VARCHAR2(100)  NOT NULL ,
	title                VARCHAR2(100)  NOT NULL ,
	author               VARCHAR2(100)  NOT NULL ,
	bookType             NUMBER  NOT NULL ,
	price                NUMBER  NULL ,
	imageUrl             VARCHAR2(2000)  NULL ,
	description          VARCHAR2(2000)  NULL ,
	publisher            VARCHAR2(100)  NULL ,
	pubDate              DATE  NULL ,
	sellerId             VARCHAR2(50)  NULL ,
	CONSTRAINT  XPKbook PRIMARY KEY (bookId),
	CONSTRAINT sellerId FOREIGN KEY (sellerId) REFERENCES clients (clientId)
);

CREATE TABLE auction_book
(
	auctionBookId        NUMBER  NOT NULL ,
	startPrice           NUMBER  NULL ,
	status               NUMBER  NULL ,
	bookId               NUMBER  NOT NULL ,
	CONSTRAINT  XPKauctionBook PRIMARY KEY (auctionBookId),
	CONSTRAINT R_28 FOREIGN KEY (bookId) REFERENCES book (bookId)
);

CREATE TABLE used_book
(
	usdeBookId           NUMBER  NOT NULL ,
	status               NUMBER  NULL ,
	bookId               NUMBER  NOT NULL ,
	CONSTRAINT  XPKusedBook PRIMARY KEY (usdeBookId),
	CONSTRAINT R_29 FOREIGN KEY (bookId) REFERENCES book (bookId)
);

CREATE TABLE bids
(
	bidId                NUMBER  NOT NULL ,
	auctionBookId        NUMBER  NOT NULL ,
	bidderId             VARCHAR2(50)  NOT NULL ,
	CONSTRAINT  XPKbids PRIMARY KEY (bidId),
	CONSTRAINT R_33 FOREIGN KEY (auctionBookId) REFERENCES auction_book (auctionBookId),
	CONSTRAINT R_34 FOREIGN KEY (bidderId) REFERENCES clients (clientId)
);

CREATE TABLE review
(
	reviewId             NUMBER  NOT NULL ,
	clientId             VARCHAR2(50)  NOT NULL ,
	bookId               NUMBER  NULL ,
	title                VARCHAR2(2000)  NOT NULL ,
	contents             VARCHAR2(2000)  NULL ,
	createDate                 DATE  NOT NULL ,
	CONSTRAINT  XPKreview PRIMARY KEY (reviewId),
	CONSTRAINT R_35 FOREIGN KEY (bookId) REFERENCES book (bookId) ON DELETE SET NULL,
	CONSTRAINT R_51 FOREIGN KEY (clientId) REFERENCES clients (clientId)
);

CREATE TABLE cart
(
	cartId               NUMBER  NOT NULL ,
	clientId               VARCHAR2(50)  NOT NULL ,
	CONSTRAINT  XPKcart PRIMARY KEY (cartId),
	CONSTRAINT R_37 FOREIGN KEY (clientId) REFERENCES clients (clientId)
);

CREATE TABLE cart_item
(
	cartItemId           NUMBER  NOT NULL ,
	bookId               NUMBER  NOT NULL ,
	quantity             NUMBER  NULL ,
	cartId               NUMBER  NOT NULL ,
	CONSTRAINT  XPKcartItem PRIMARY KEY (cartItemId),
	CONSTRAINT R_31 FOREIGN KEY (cartId) REFERENCES cart (cartId),
	CONSTRAINT R_32 FOREIGN KEY (bookId) REFERENCES book (bookId)
);

CREATE TABLE "orders"
(
	orderId              NUMBER  NOT NULL ,
	orderDate            DATE  NOT NULL ,
	name                 VARCHAR2(50)  NOT NULL ,
	phoneNumber          VARCHAR2(50)  NOT NULL ,
	zipCode              VARCHAR2(20)  NOT NULL ,
	address1             VARCHAR2(100)  NOT NULL ,
	address2             VARCHAR2(100)  NULL ,
	shipMessage          VARCHAR2(1000)  NULL ,
	price                NUMBER  NULL ,
	clientId               VARCHAR2(50)  NOT NULL ,
	CONSTRAINT  XPKorder PRIMARY KEY (orderId),
	CONSTRAINT R_53 FOREIGN KEY (clientId) REFERENCES clients (clientId)
);

CREATE TABLE auction_book_order
(
	auctionOrderId       NUMBER  NOT NULL ,
	orderId              NUMBER  NOT NULL ,
	auctionBookId        NUMBER  NOT NULL ,
	bidId                NUMBER  NOT NULL ,
	CONSTRAINT  XPKauctionBookOrder PRIMARY KEY (auctionOrderId),
	CONSTRAINT R_41 FOREIGN KEY (orderId) REFERENCES orders (orderId),
	CONSTRAINT R_50 FOREIGN KEY (auctionBookId) REFERENCES auction_book (auctionBookId),
	CONSTRAINT R_55 FOREIGN KEY (bidId) REFERENCES bids (bidId)
);

CREATE TABLE order_item
(
	orderItemId          INTEGER  NOT NULL ,
	orderId              NUMBER  NOT NULL ,
	quantity             NUMBER  NOT NULL ,
	bookId               NUMBER  NOT NULL ,
	CONSTRAINT  XPKorderItem PRIMARY KEY (orderItemId),
	CONSTRAINT R_20 FOREIGN KEY (orderId) REFERENCES orders (orderId) ON DELETE SET NULL,
	CONSTRAINT R_43 FOREIGN KEY (orderId) REFERENCES orders (orderId),
	CONSTRAINT R_52 FOREIGN KEY (bookId) REFERENCES book (bookId)
);


CREATE TABLE used_book_order
(
	usedOrderId          NUMBER  NOT NULL ,
	orderId              NUMBER  NOT NULL ,
	usdeBookId           NUMBER  NOT NULL ,
	CONSTRAINT  XPKusedBookOrder PRIMARY KEY (usedOrderId),
	CONSTRAINT R_47 FOREIGN KEY (orderId) REFERENCES orders (orderId),
	CONSTRAINT R_49 FOREIGN KEY (usdeBookId) REFERENCES used_book (usdeBookId)
);


GRANT ALL ON used_book_order TO public;
GRANT ALL ON auction_book_order TO public;
GRANT ALL ON order_item TO public;
GRANT ALL ON cart_item TO public;
GRANT ALL ON cart TO public;
GRANT ALL ON review TO public;
GRANT ALL ON orders TO public;
GRANT ALL ON bids TO public;
GRANT ALL ON auction_book TO public;
GRANT ALL ON used_book TO public;
GRANT ALL ON book TO public;
GRANT ALL ON clients TO public;