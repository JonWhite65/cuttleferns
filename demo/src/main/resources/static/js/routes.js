angular.
	module('app').
		config([ '$routeProvider', function config(routeProvider) {
			
			routeProvider.
				when('/peoples',{
					templateUrl: "js/people.html",
					controller:'PeopleController',
					controllerAs:'pC'
				}).
				when('/peoples/:id',{
					templateUrl:"js/individual.html",
					controller:'PersonController',
					controllerAs:'pC1'
					
				}).
				otherwise('/peoples');
				
		} ]);
