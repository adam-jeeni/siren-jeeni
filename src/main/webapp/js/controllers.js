    var searchResultApp = angular.module('searchResultApp', []);
     
    searchResultApp.controller('ResultListCtrl', function ($scope) {
    $scope.results = 
    {"classes":["searchResults"],"properties":{"nscanned":165,"size":3,"timeSecs":7.56E-4,"searchText":"text"},"entities":[{"rel":["http://api.jeeni.co.uk/seerSearch/rels/results"],"classes":["searchResults"],"properties":{"docId":"52eab7e87a580ee556e008ff","score":2.83125},"entities":[{"rel":["http://api.jeeni.co.uk/seerSearch/rels/product"],"classes":["product"],"properties":{"searchDescription":"Stanley Air Tool Accessories, Weight 1.75kg.","mediumDescription":"Stanley Air Tool Accessories","specification":"Weight 1.75kg.","longDescription":"Stanley Air Tool Accessories Kit 8Pcs","brand":"Stanley","sku":"93964"},"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/product/93964"}]}],"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/seerSearch"}]},{"rel":["http://api.jeeni.co.uk/seerSearch/rels/results"],"classes":["searchResults"],"properties":{"docId":"52eab7e87a580ee556e00902","score":2.65},"entities":[{"rel":["http://api.jeeni.co.uk/seerSearch/rels/product"],"classes":["product"],"properties":{"searchDescription":"Air Die Grinder Kit 15Pcs, Weight 1kg.","mediumDescription":"Air Die Grinder Kit 15Pcs","specification":"Weight 1kg.","longDescription":"Stanley Air Die Grinder Kit 15Pcs","brand":"Stanley","sku":"85387"},"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/product/85387"}]}],"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/seerSearch"}]},{"rel":["http://api.jeeni.co.uk/seerSearch/rels/results"],"classes":["searchResults"],"properties":{"docId":"52eab7e87a580ee556e00924","score":2.7232142857142856},"entities":[{"rel":["http://api.jeeni.co.uk/seerSearch/rels/product"],"classes":["product"],"properties":{"brand":"Stanley","specification":"Max. operating pressure 150psi / 10.5bar.","longDescription":"Stanley CPACK10 Air Hose Kit 10m","sku":"13692","searchDescription":"Stanley Air Hose Kit 10m, Max. operating pressure 150psi / 10.5bar., Rubber Hose for Increased Durability, �\" Male \u0026 Female Couplers \u0026 Tails for Flexibility, High Flow Rate","bulletPoint3":"High Flow Rate","mediumDescription":"Stanley Air Hose Kit 10m","bulletPoint2":"�\" Male \u0026 Female Couplers \u0026 Tails for Flexibility","bulletPoint1":"Rubber Hose for Increased Durability"},"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/product/13692"}]}],"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/seerSearch"}]}],"links":[{"rel":["self"],"href":"http://api.jeeni.co.uk/seerSearch?q\u003d\"Stanley Air\""}]}
    ;
    });