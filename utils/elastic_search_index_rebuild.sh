INDEX_URL="http://cmput301.softwareprocess.es:8080/cmput301w14t12"

function log() {
	if [[ $? == 0 ]]; then
		echo -e "\n$@"
	else
		echo -e "\nFAILED: $@"
		exit
	fi
}

function endpoint() {
	echo -n "${INDEX_URL}/$1?pretty"
}

# Delete the current index
curl -XDELETE `endpoint`
log "Deleted index."

# Create the new index with the right mapping
curl -XPOST `endpoint` -d'
{
    "mappings": {
        "wyatt": {
           "properties": {
             "timestamp": {
                 "type" : "date"
            }
        }
    }
    }
}'
log "Created index."

curl -XGET `endpoint _mapping`
log "Got mapping."