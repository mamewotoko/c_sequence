// VCG.java		Created      : Tue Dec 23 22:06:23 2003
//			Last modified: Sun Dec 25 17:47:18 2016
// Compile: javac VCG.java #
// Execute: java VCG #
// FTP Directory: sources/java #
//------------------------------------------------------------
//
import java.io.*;
import java.util.*;

public class VCG {

    static public class Node implements Comparable {
        public String _label = null;
        public String _title = null;
        public String[] _info = new String[3];
        public String _color = null;

        public Node() 
        {    
            _info[0] = null;
            _info[1] = null;
            _info[2] = null;
        }
    
        public Node(String label, String name) 
        {
            _info[0] = null;
            _info[1] = null;
            _info[2] = null;
	    
            _label = label;
            _title = name;
        }
    
        public String toString() 
        {
            return _label;
        }
	
        public void setColor(String c)
        {
            _color = c;
        }

        public String getColor()
        {
            return _color;
        }

        public int compareTo(Object o) 
        {
            Node n = (Node)o;
            int c = (this._label).compareTo(n._label);
            return (c != 0) ? c : this._title.compareTo(n._title);
        }
	
    }
    
    static public class Edge implements Comparable {
	
        public String _label = null;
        public String _source = null;
        public String _target = null;
	
        public Edge() {    }
	
        public Edge(String source, String target) 
        {
            _source = source;
            _target = target;
        }
        public Edge(String source, String target, String label) 
        {
            _source = source;
            _target = target;
            _label = label;
        }
    
        public String toString() 
        {
            return _source + ":" + _target;
        }
    
        public int compareTo(Object o) 
        {
            Edge e = (Edge)o;
            int c = (this._source).compareTo(e._source);
            return (c != 0) ? c : this._target.compareTo(e._target);
        }
    }

    public static class NodeSet extends TreeSet {
	
        public NodeSet(Comparator c)
        {
            super(c);
        }
	
        public boolean add(Object o)
        {
            //check that title is unique
            Node n = (Node)o;
            return super.add(n);
        }
    }

    public static class EdgeSet extends TreeSet {
        public boolean add(Object o)
        {
            Edge n = (Edge)o;
            return super.add(n);
        }
    }

    public static class Graph {
        private NodeSet _node_set;
        private EdgeSet _edge_set;

        public static class TitleComparator 
            implements Comparator {
            public TitleComparator() {}
            public int compare(Object o1, Object o2)
            {
                Node n1 = (Node)o1;
                Node n2 = (Node)o2;
                int result = n1._title.compareTo(n2._title);
                return n1._title.compareTo(n2._title);
            }
	    
            public boolean equals(Object o) 
            {
                return o == this;
            }
        }
	
        public void check() throws Exception
        {
            //check that edge is connected to valid node
            for(Iterator i = _edge_set.iterator(); i.hasNext(); /* nothing */) {
                Edge e = (Edge)i.next();
                if(! _node_set.contains(new Node(e._source, e._source))) {
                    throw new Exception(e._source + " is not contained in node set");
                }
                if(! _node_set.contains(new Node(e._target, e._target))) {
                    throw new Exception(e._target + " is not contained in node set");
                }
            }
        }  

        public void addNode(Node n)
        {
            _node_set.add(n);
        }

        public void addEdge(Edge e)
        {
            _edge_set.add(e);
        }

        public Graph() 
        {
            _node_set = new NodeSet(new TitleComparator());
            _edge_set = new EdgeSet();
        }
	
        public String toVCG()
        {
            StringBuffer sb = new StringBuffer();
            sb.append("graph: {\n");

            for(Iterator i = _node_set.iterator(); i.hasNext(); /* nothing */) {
                Node n = (Node)i.next();
                sb.append("  node: { label: \""+n._label+"\" title: \""+n._title+"\"" +((n.getColor() != null) ? " color: "+n.getColor() :"")+" }\n");
            }
            for(Iterator i = _edge_set.iterator(); i.hasNext(); /* nothing */) {
                Edge e = (Edge)i.next();
                sb.append("  edge: { sourcename: \""+e._source+"\" targetname: \""+e._target+"\" }\n");
            }
            sb.append("}\n");
            return sb.toString();
        }
	
        public String toString()
        {
            String result = "";
            for(Iterator i = _node_set.iterator(); i.hasNext(); /* nothing */) {
                result += i.next().toString()+"\n";
            }
            result += "-----------------------------\n";
            for(Iterator i = _edge_set.iterator(); i.hasNext(); /* nothing */) {
                result += i.next().toString()+"\n";
            }
            return result;
        }
    }
}
