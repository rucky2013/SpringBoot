	  	function populateWebsiteDetails(jd) {
				if (jd.blog !== undefined && jd.blog !== null) {
					$('#website').html('<h4>Website</h4><a href="' + jd.blog + '">' + jd.blog +'</a>');	
				}
			}
			function populateHeaderDetails(jd, name) {
				$('#header').html('<h4>' + name + '</h4>' + jd.userType);
			}
			function populateProfileDetails(jd, name) {
				var repositoryWord = "";
				if (jd.public_repos > 1) {
				    repositoryWord = "repositories";
				} else {
					repositoryWord = "repository";
				}
				var followersWord = "";
				if (jd.followers > 1) {
				    followersWord = " followers";
				} else {
					followersWord = " follower";
				}
              	$('#profile').html('<table><tr><td><h4>Github Profile</h4></td><td> On GitHub,' + name + ' is a developer <a href="https://github.com/' + name + '?tab=repositories">with ' + jd.public_repos + ' public ' + repositoryWord + ' </a> and  <a href="https://github.com/' + name +'/followers">' + jd.followers + followersWord  + '</a></td></tr></table>');
			}
         $(document).ready(function() {
            $("#driver").click(function(event){
				var rootUrl = "http://localhost:8080/resume/";
				var name = $("#name").val();
				$("#btnLabel").hide();
				$("#name").hide();
				$("#driver").hide();
				$.getJSON(rootUrl + 'user/' + name, function(jd) {
                  	populateHeaderDetails(jd, name);
					populateWebsiteDetails(jd);
					populateProfileDetails(jd, name);
               	});
            	$.getJSON(rootUrl + 'languages/' + name, function(jd) {
            		var languages = "<h4>Languages</h4><table border='1'><tr>";
            		$.each(jd, function(i, item) {
						if (i%3 == 0) {
            				languages += "</tr><tr>";
            			}
            			languages += '<td><a href=https://github.com/languages/' + jd[i].language + '>' + jd[i].language + '(' + (jd[i].percent * 100).toFixed(2) + '%)</a></td>';
            		});
					languages += '</tr></table>'
            		$('#language').html(languages);
                });
            });
         });
