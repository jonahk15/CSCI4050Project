<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  
	shoppingCart.html
	
	Austin Schultz (als93978)
	
	This page implements the Checkout page design found
	on the Outline document on the Google Drive.
-->

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="shoppingCartStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Shopping Cart</title>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/0318fd2bd1.js" crossorigin="anonymous"></script>
	</head>
	
	<body>
		<!-- Main container  -->
		<div class="mainContainer">
		
			<!-- Header -->
			<div class="headerContainer">
 				<nav class="navbar navbar-expand-lg navbar-custom">
 					<a class="navbar-brand" href="homepageWithUserIcon.html">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-book-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						  <path fill-rule="evenodd" d="M8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
						</svg>
						
						BookBay
 					</a>
 					
 					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
 						<span class="navbar-toggler-icon"></span>
 					</button>
 					
 					<div class="collapse navbar-collapse" id="navbarSupportedContent">
 						<ul class="navbar-nav mr-auto">
 							<li>
 								<a class="nav-link" href="#">Action</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Comedy</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Family</a>
 							</li>
 							
 						    <li>
 								<a class="nav-link" href="#">Kids</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Mystery</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Horror</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Romance</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Thriller</a>
 							</li>
 						</ul>
 						
 						<form class="form-inline mr-auto" action="/WebContent/search.html">
 							<input class="form-control mr-sm-2" type="search" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit" href="search.html">Search</button>
 						</form>
 						
 						<div class="dropdown">
							<a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="img/user-icon.png" class="img-fluid user-icon" alt="User Icon">
							</a>
							<form id="logoutForm" action="Logout" method="post">
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a id="logoutBtn" class="dropdown-item">Log off</a>
									<a class="dropdown-item" href="orderhistory.html">Order history</a>
									<a class="dropdown-item" href="accountSettings.html">Account settings</a>
								</div>
							</form>
						</div>
 						
 						<a class="nav-link" href="shoppingCart.jsp">
							 <i class="fas fa-shopping-cart fa-2x"></i>
							 <span class="badge badge-dark badge-pill">4</span>
 						</a>
 					</div>
 				</nav>
			</div>
			
			<!-- Content  -->
			<div class="contentContainer">
				<div class="content">
					<div class="shoppingCart">
						<h5 class="d-flex justify-content-between mb-3">
							Shopping Cart
							<span class="badge badge-dark badge-pill">4</span>
						</h5>
						
						<div class="sidebar">
							<ul class="list-group">
								<li class="list-group-item">
									<img src="img/bookCover1.jpg" class="cart-img mr-3" alt="" width="95" height="110">
									
									<div class="media-body">
										<h5 class="mt-0 mb-1">Head First Web Design</h5>
										
										<p>
											Ethan Watrall, Jeff Siarto
										</p>
																				
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										
										<br/>
										<br/>
										
										<label for="quantity">Quantity:</label>
										<input type="number" id="quantity1" class="quantity" name="quantity" min="1" max="50" value="1">
										<p class="mt-2">$9.99</p>
										
										<button type="button" class="btn btn-outline-secondary btn-sm">Remove</button>
									</div>
								</li>
								
								<li class="list-group-item">
									<img src="img/bookCover2.jpg" class="cart-img mr-3" alt="" width="95" height="110">
									
									<div class="media-body">
										<h5 class="mt-0 mb-1">The Great Gatsby</h5>
										
										<p>
											F. Scott Fitzgerald
										</p>
										
																														
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										
										<br/>
										<br/>
										
										<label for="quantity">Quantity:</label>
										<input type="number" id="quantity2" class="quantity" name="quantity" min="1" max="50" value="1">
										<p class="mt-2">$9.99</p>
										
										<button type="button" class="btn btn-outline-secondary btn-sm">Remove</button>
									</div>
								</li>
								
																
								<li class="list-group-item">
									<img src="img/bookCover3.jpg" class="cart-img mr-3" alt="" width="95" height="110">
									
									<div class="media-body">
										<h5 class="mt-0 mb-1">The Catcher in the Rye</h5>
										
										<p>
											J.D. Saligner
										</p>
																														
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										
										<br/>
										<br/>
										
										<label for="quantity">Quantity:</label>
										<input type="number" id="quantity3" class="quantity" name="quantity" min="1" max="50" value="1">
										<p class="mt-2">$9.99</p>
										
										<button type="button" class="btn btn-outline-secondary btn-sm">Remove</button>
									</div>
								</li>
								
																
								<li class="list-group-item">
									<img src="img/bookCover4.jpg" class="cart-img mr-3" alt="" width="95" height="110">
									
									<div class="media-body">
										<h5 class="mt-0 mb-1">Catch-22</h5>
										
										<p>
											Joseph Heller
										</p>
																														
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										<i class="fas fa-star fa-xs"></i>
										
										<br/>
										<br/>
										
										<label for="quantity">Quantity:</label>
										<input type="number" id="quantity4" class="quantity" name="quantity" min="1" max="50" value="1">
										<p class="mt-2">$9.99</p>
										
										<button type="button" class="btn btn-outline-secondary btn-sm">Remove</button>
									</div>
								</li>
							</ul>
						</div>
						<!-- <div></div> -->
					</div>
					
					<div class="infoArea">
						<div class="infoAreaGrid">
 							<div class="row">
 								<div class="col">
 									<div class="card checkoutInfoCard">
 										<div class="card-header">
 											Payment Information
 										</div>
										
 										<div class="card-body">
 											<ul class="list-unstyled">
 												<li class="media">
													<input type="radio" class="form-check-input form-check-inline" name="paymentInfo" value="paymentInfo1">
													<img src="img/discoverLogo.png" class="mr-3">
												   
												   <div class="media-body">
													   <div>
														   **** **** **** 8920
													   </div>
													   
													   <!-- 
														   TODO: Name get struched up when too long. I actually want
														   it directly below the card number, but Bootstrap isn't letting
														   that happen. Fix later.
														-->
													   <p>
														   (John Doe Smith)
													   </p>
													   
												   </div>
		    									
		    										<a href="#" class="btn btn-secondary editPaymentInfoBtn">Edit</a>
		    									</li>
		    									
		    									<li class="media paymentInfoItem">
													<input type="radio" class="paymentInfoRadio form-check-input" name="paymentInfo" value="paymentInfo2">
													<img src="img/discoverLogo.png" class="mr-3">
												   
												   <div class="media-body">
													   <div>
														   **** **** **** 5673
													   </div>
																											   
													   <p>
														   (John Doe Smith)
													   </p>
												   </div>
		    										
		    										<a href="#" class="btn btn-secondary editPaymentInfoBtn">Edit</a>
		    									</li>
    										</ul>
    										
    										<hr>
    										
     										<a href="#" class="btn btn-primary">Add New Payment Method</a>
 										</div>
 									</div>
 								</div>
 							</div>
 							
 							<div class="row">					
 								<div class="col">
								 	<div class="card checkoutInfoCard">
 										<div class="card-header">
 											Shipping Information
 										</div>
										
 										<div class="card-body">
											<div class="shippingInfoItem">
												<div class="addressInfo">
													<p>
														Name: John Doe Smith
													</p>
													
													<p>
														Address: 3243 John Doe Blvd, Athens, GA, 30606
													</p>
												</div>
												
												<div class="shippingInputOptions">
													<input type="radio" class="shippingInfoRadio" id="shippingInfo1" name="shippingInfo" value="shippingInfo1" checked>
													
													<a href="#" class="btn btn-secondary editShippingInfoBtn">Edit</a>
												</div>
											</div>
											
											<div class="shippingInfoItem shippingInfoItemTopMargin">
												<div class="addressInfo">
													<p>
														Name: John Doe Smith
													</p>
													
													<p>
														Address: 3452 Vacation Drive, Honolulu, HI, 96814
													</p>
												</div>
												
												<div class="shippingInputOptions">
													<input type="radio" class="shippingInfoRadio" id="shippingInfo1" name="shippingInfo" value="shippingInfo1">
													
													<a href="#" class="btn btn-secondary editShippingInfoBtn">Edit</a>
												</div>
											</div>
    										
    										<hr>
    										
     										<a href="#" class="btn btn-primary">Add New Shipping Address</a>
 										</div>
 									</div>
 									
 									<div class="nextBtnContainer">
										<a href="orderSummary.html" id="next" class="btn btn-primary btn-lg">Next</a>
 									</div>
 							</div>
						</div>
					</div>
				</div>
	    	</div>
	    	
	    	<!-- Footer -->
	     	<div class="footerContainer">
				 <footer class="footer">
					<nav class="navbar navbar-expand-lg navbar-custom">
						<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<span class="navbar-text mr-auto">
								Team A5 - CSCI4050
							</span>
						
							<ul class="navbar-nav ml-auto">
								<li>
									<a class="nav-link" href="#">About</a>
								</li>
								
								<li>
									<a class="nav-link" href="#">Careers</a>
								</li>
								
								<li>
									<a class="nav-link" href="#">Contact</a>
								</li>
								
								<li>
									<a class="nav-link" href="#">Privacy</a>
								</li>
								
								<li>
									<a class="nav-link" href="#">Sitemap</a>
								</li>
								
								<li>
									<a class="nav-link" href="#">Terms of Use</a>
								</li>
							</ul>
						</div>
					</nav>
				</footer>	
	     	</div>
    	</div>
		</div>
		<script type="text/javascript" src="shoppingCart.js"></script>	 
	</body>
</html>