// See: https://docs.dgraph.io/mutations/#json-mutation-format

const traversal = require('tosca.lib.traversal');
const tosca = require('tosca.lib.utils');

traversal.coerce();

let vertexItems = [];
let cloutItem = {'clout.vertex': vertexItems};
let items = [cloutItem];

for (let vertexId in clout.vertexes) {
	let vertex = clout.vertexes[vertexId];

	let vertexItem = {uid: '_:clout.vertex.' + vertexId, 'clout.edge': []};

	if (tosca.isTosca(vertex, 'NodeTemplate'))
		fillNodeTemplate(vertexItem, vertex.properties);

	for (let e = 0, l = vertex.edgesOut.length; e < l; e++) {
		let edge = vertex.edgesOut[e];
		fillEdge(vertexItem, edge);
	}

	vertexItems.push(vertexItem);
}

function fillEdge(item, edge) {
	let edgeItem = {uid: '_:clout.vertex.' + edge.targetID};

	if (tosca.isTosca(edge, 'Relationship'))
		fillRelationship(edgeItem, edge.properties);

	item['clout.edge'].push(edgeItem);
}

function fillTosca(item, entity, type_, prefix) {
	if (prefix === undefined)
		prefix = '';
	item[prefix + 'tosca.entity'] = type_;
	item[prefix + 'tosca.name'] = entity.name;
	item[prefix + 'tosca.description'] = entity.description;
	item[prefix + 'tosca.types'] = JSON.stringify(entity.types);
	item[prefix + 'tosca.properties'] = JSON.stringify(entity.properties);
	item[prefix + 'tosca.attributes'] = JSON.stringify(entity.attributes);
}

function fillNodeTemplate(item, nodeTemplate) {
	fillTosca(item, nodeTemplate, 'nodeTemplate');

	item.capabilities = [];
	for (let name in nodeTemplate.capabilities) {
		let capability = nodeTemplate.capabilities[name];
		let capabilityItem = {};
		fillTosca(capabilityItem, capability, 'capability');
		item.capabilities.push(capabilityItem);
	}
}

function fillRelationship(item, relationship) {
	// As facets
	fillTosca(item, relationship, 'relationship', 'clout.edge|');
}

puccini.format = 'json';
puccini.write({set: items});