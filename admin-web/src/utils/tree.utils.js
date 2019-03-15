// tree 工具

export function buildTreeNode(nodes, titleKey, nodeKey) {
  return nodes.map(item => {
    const res = {};
    if (item.children) {
      res.children = buildTreeNode(item.children, titleKey, nodeKey);
    }
    res.title = `${item.id}-${item[titleKey]}`;
    res.key = item[nodeKey];
    return res;
  });
}

// @primary
function findNodes(id, nodes) {
  const res = [];
  for (let i = 0; i < nodes.length; i += 1) {
    const node = nodes[i];
    if (node.key === id) {
      res.push(node.key);
      break;
    } else if (node.children) {
      const childNodes = findNodes(id, node.children);
      for (let j = 0; j < childNodes.length; j += 1) {
        res.push(childNodes[j]);
      }
    }
  }
  return res;
}

export function findAllNodes(resourceIds, nodes) {
  console.log('resourceIds', resourceIds);

  const findNodesArray = [];
  for (let i = 0; i < resourceIds.length; i += 1) {
    const findNodesData = findNodes(resourceIds[i], nodes);
    console.log('findNodesData', findNodesData);
    if (findNodesData) {
      for (let j = 0; j < findNodesData.length; j += 1) {
        const jD = findNodesData[j];
        if (findNodesArray.indexOf(jD) === -1) {
          findNodesArray.push(jD);
        }
      }
    }
  }
  return findNodesArray;
}

export function findCheckedKeys(nodes) {
  let res = [];
  for (let i = 0; i < nodes.length; i += 1) {
    const node = nodes[i];
    if (node.children) {
      const findChildrenNodes = findCheckedKeys(node.children);
      if (findChildrenNodes) {
        res = res.concat(findChildrenNodes);
      }
    } else if (node.assigned === true) {
      res.push(node.id);
    }
  }
  return res;
}
