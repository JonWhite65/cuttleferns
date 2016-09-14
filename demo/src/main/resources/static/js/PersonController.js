angular.module('app').controller('PersonController', ['PersonService', '$routeParams','$location', function(PersonService, $routeParams,location)
{
	
	var ctrl = this;
	this.newInterest=null;
	this.newGroup=null;
	PersonService.getPersonData($routeParams.id).then(function(result){
		ctrl.person = result.data;
	});
	this.submit= function(){
		console.dir()
		if(this.newInterest!==null){
		this.person.interest.push({ name:newInterest});
		}if(this.newGroup!==null){
			this.person.groups.push({ name:newGroup});
		}
		PersonService.patchPersonData(this.person).then(function(){location.path('#/peoples')});
		
	
	}
}]);