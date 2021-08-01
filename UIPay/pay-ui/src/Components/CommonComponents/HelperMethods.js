class HelperMethods 
{
    static GetServerIP =( ) =>
    {
        let protocol='http';
        let serverIP='192.168.43.241:8086';
        return protocol + '://' + serverIP + '/';
    };
}

export default HelperMethods;